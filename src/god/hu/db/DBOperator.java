package god.hu.db;

import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;
import god.hu.usage.abs.DBOperation;
import god.hu.usage.abs.DVDOperate;
import god.hu.usage.abs.State;

import java.sql.*;

public class DBOperator implements DVDOperate, DBOperation {
    private static Connection con = null;

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

    private Statement statement = null;

    @Override
    public void addReader(Reader reader) {

    }

    @Override
    public Reader getReader(int id) {
        return null;
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
    public void addDVD(DVD dvd) {
        try {
            // render.setHeaders("ID", "TIME", "STATE", "NAME");
            statement = con.createStatement();
            statement.executeQuery("insert into dvd values(" + dvd.getId() + ",'" + dvd.getTime().getSerial() + "'," + (dvd.getState() == State.NOT_AVAI ? 1 : 0) + ",'" + dvd.getName() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeDVD(DVD dvd) {

    }

    @Override
    public void removeDVDById(Integer id) {
        try {
            statement = con.createStatement();
            statement.executeQuery("delete from dvd where id=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renew(int id, Time time) {

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
}
