package god.hu.db;

import god.hu.model.*;
import god.hu.model.Time;
import god.hu.usage.abs.MDBOperation;
import god.hu.usage.abs.DVDOperate;
import god.hu.usage.abs.State;

import java.sql.*;
import java.util.ArrayList;

public class MDBOperator implements DVDOperate, MDBOperation {
    private static Connection con = null;
    private DVDManager manager;
    private Statement statement = null;


    static {
        //step1 load the driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //step2 create  the connection object
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:god", "scott", "tiger");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MDBOperator() {
        manager = Lab.getManager();
    }

    @Override
    public void removeDVD(DVD dvd) {

    }

    @Override
    public DVD borrow(int id) {
        return null;
    }

    @Override
    public DVD revert(int id) {
        return null;
    }

    @Override
    public void renew(int id, Time time) {

    }

    @Override
    public void addReader(Reader reader) {
        try {
            statement = con.createStatement();
            statement.executeQuery("insert into reader values('" + reader.getName() + "'," + reader.getId() + ",'" + reader.getDvd_list_id() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Reader getReader(int id) {
        Reader reader = null;
        ResultSet set = null;
        try {
            statement = con.createStatement();
            set = statement.executeQuery("select * from reader where id="+id);
            if(set.next()){
                reader = new Reader();
                reader.setName(set.getString(1));
                reader.setId(set.getInt(2));
                reader.setDvd_list_id(set.getInt(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reader;
    }

    @Override
    public void addDVD(DVD dvd) {
        try {
            statement = con.createStatement();
            statement.executeQuery("insert into dvd values(" + dvd.getId() + ",'" + dvd.getTime().getSerial() + "'," + (dvd.getState() == State.NOT_AVAI ? 1 : 0) + ",'" + dvd.getName() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeDVDById(Integer id) throws Exception {
        try {
            statement = con.createStatement();
            int deleted = statement.executeUpdate("delete from dvd where id=" + id);
            if (deleted == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public DVD getDVDById(Integer id) throws Exception {
        ResultSet result = null;
        DVD dvd = null;
        try {
            statement = con.createStatement();
            result = statement.executeQuery("select * from dvd where id=" + id);
            if (result.next()) {
                dvd = new DVD.Builder()
                        .setID(result.getInt(1))
                        .setTime(new Time.Builder().setSerial(result.getString(2)).build())
                        .setState(result.getInt(3) == 0 ? State.ON_SHELF : State.NOT_AVAI)
                        .setName(result.getString(4))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dvd;
    }

    @Override
    public Integer selectCountByName(String name) throws Exception {
        ResultSet rt = null;
        try {
            statement = con.createStatement();
            rt = statement.executeQuery("select count(NAME) from dvd where name='" + name + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert rt != null;
        if (rt.next())
            return rt.getInt(1);
        else return null;
    }

    @Override
    public ArrayList<DVD> selectAll() throws Exception {
        //TODO:添加count总量字段
        ResultSet rt1 = null;
        ArrayList<DVD> result = new ArrayList<DVD>();
        try {
            statement = con.createStatement();
            rt1 = statement.executeQuery("select name,time,state,id from dvd");

            while (rt1.next()) {
                result.add(new DVD.Builder()
                        .setName(rt1.getString(1))
                        .setTime(new Time.Builder().setSerial(rt1.getString(2)).build())
                        .setState(rt1.getString(3).equals("0") ? State.ON_SHELF : State.NOT_AVAI)
                        .setID(Integer.parseInt(rt1.getString(4)))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rt1 != null;
            rt1.close();
        }
        manager.setDvds(result);
        return result;
    }

    @Override
    public ArrayList<String> selectDistinctName() throws Exception {
        ResultSet rt2 = null;
        ArrayList<String> result = new ArrayList<String>();
        try {
            statement = con.createStatement();
            rt2 = statement.executeQuery("select distinct name from dvd");
            while (rt2.next()) {
                result.add(rt2.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rt2 != null;
            rt2.close();
        }
        return result;
    }

    public ArrayList<Reader> getReaders() throws SQLException {
        ArrayList<Reader> readers  =new ArrayList<Reader>();;
        ResultSet rt2 = null;
        try {
            statement = con.createStatement();
            rt2 = statement.executeQuery("select * from reader");
            while (rt2.next()) {
                readers.add(new Reader(rt2.getString(1),rt2.getInt(2),rt2.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rt2 != null;
            rt2.close();
        }
        return readers;
    }
}
