package god.hu.db;

import god.hu.Main;
import god.hu.model.*;
import god.hu.model.Time;
import god.hu.usage.abs.MDBOperation;
import god.hu.usage.abs.DVDOperate;
import god.hu.usage.abs.State;
import god.hu.usage.tool.cli.ConsoleColors;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;

public class MDBOperator implements DVDOperate, MDBOperation {
    public static volatile String ip = "", name = "", pwd = "";
    private static Connection con = null;
    private static java.util.Date date;

    static {
        //step1 load the driver class
        try {
            pwd = Main.pwd;
            ip = Main.ip;
            name = Main.name;

            if (ip.equals("")) {
                ip = "192.168.113.104";
            }
            if (name.equals("")) {
                name = "dvd_manager";
            }
            if (pwd.equals("")) {
                pwd = "hyl3356";
            }

            Class.forName("com.mysql.jdbc.Driver");
            //step2 create  the connection object
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/dvd_manager", name, pwd);

            System.out.println(ConsoleColors.YELLOW + "DB<-连接成功->DB IP为:" + ip + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ConsoleColors.RED + "数据库连接失败,请重试!\n仔细检查ip是否正确!\n退出系统中..." + ConsoleColors.RESET);
            System.exit(0);
        }
    }

    private DVDManager manager;
    private Statement statement = null;

    public MDBOperator() {
        manager = Lab.getManager();
    }

    @Override
    public void removeDVD(DVD dvd) {

    }

    @Override
    public void updateTimeBySerial(String serial, Time time) throws Exception {
        try {
            //TODO: fix update failed;
            statement = con.createStatement();
            String sql = "update time set borrow_time='" + time.getBorrowTime() + "',revert_time='" + time.getRevertTime() + "',renew_time='" + time.getRenewTime() + "' where serial='" + serial + "'";
            int code = statement.executeUpdate(sql);
            if (code == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
    }

    @Override
    public void updateReaderListByReaderId(Integer reader_id, DVD dvd) throws Exception {
        try {
            int dvd_n = findEmptyListInReaderListByReaderId(reader_id);
            if (dvd_n == -1) {
                System.out.println("已经达到借阅上限!");
                throw new Exception();
            }

            String dvd_num = "dvd" + dvd_n + "_id";
            statement = con.createStatement();
            String sql = "update reader_list set " + dvd_num + "=" + dvd.getId() + " where reader_id='" + reader_id + "'";
            int code = statement.executeUpdate(sql);
            if (code == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
    }

    @Override
    public void removeDVDFromReaderListByReaderId(Integer reader_id, DVD dvd) throws Exception {
        try {
            int dvd_n = findMatchFromReaderList(reader_id, dvd.getId());
            if (dvd_n == -1) {
                System.out.println("未找到!");
                throw new Exception();
            }

            String dvd_num = "dvd" + dvd_n + "_id";
            statement = con.createStatement();
            String sql = "update reader_list set " + dvd_num + "=" + "0" + " where reader_id='" + reader_id + "'";
            int code = statement.executeUpdate(sql);
            if (code == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
    }

    @Override
    public Integer findEmptyListInReaderListByReaderId(Integer reader_id) throws Exception {
        Integer number = -1;
        try {
            statement = con.createStatement();
            String sql = "select * from reader_list where reader_id='" + reader_id + "'";
            ResultSet set = statement.executeQuery(sql);
            if (set.next()) {
                //System.out.println(set.getInt(2) + "," + set.getInt(3) + "," + set.getInt(4));
                // detect which is empty(cause default its 0),
                if (set.getInt(2) == 0)
                    return 1;
                if (set.getInt(3) == 0)
                    return 2;
                if (set.getInt(4) == 0)
                    return 3;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
        return number;
    }

    @Override
    public Integer findMatchFromReaderList(Integer reader_id, Integer dvd_id) throws Exception {
        Integer number = -1;
        try {
            statement = con.createStatement();
            String sql = "select * from reader_list where reader_id='" + reader_id + "'";
            ResultSet set = statement.executeQuery(sql);
            if (set.next()) {
                System.out.println(set.getInt(2) + "," + set.getInt(3) + "," + set.getInt(4));
                if (set.getInt(2) == dvd_id)
                    return 1;
                if (set.getInt(3) == dvd_id)
                    return 2;
                if (set.getInt(4) == dvd_id)
                    return 3;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
        return number;
    }

    @Override
    public void insertNewReaderListByIdFromReader(Integer id) throws Exception {
        try {
            statement = con.createStatement();
            String sql = "insert into reader_list values(" + id + ",0,0,0)";
            int code = statement.executeUpdate(sql);
            if (code == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
    }

    @Override
    public DVD borrow(int id, Reader reader) throws Exception {
        //TODO: fix update failed,find new way borrow;
        DVD dvd = null;
        Time time = null;
        try {
            dvd = getDVDById(id);
            if (dvd.getState() == State.NOT_AVAI) {
                System.out.println("该书不可借!");
                throw new Exception();
            }

            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from time where serial='" + dvd.getTime().getSerial() + "'");
            if (resultSet.next()) {
                date = new java.util.Date();
                time = new Time.Builder()
                        .setId(dvd.getTime().getId())
                        .setBorrowTime(dateShort())
                        .setRevertTime(dvd.getTime().getRevertTime())
                        .setRenewTime(dvd.getTime().getRenewTime())
                        .setSerial(dvd.getTime().getSerial())
                        .build();
//                System.out.println(time.toString());
            }

            dvd.setTime(time);
            assert time != null;
            // update time to table ,for reader time
            updateTimeBySerial(time.getSerial(), time);
            // insert into reader_dvd_list,passing from lab
            updateReaderListByReaderId(reader.getId(), dvd);
            // set dvd state to not_avail
            updateDVDById(dvd.getId(), dvd);
        } catch (SQLException throwables) {
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
        return dvd;
    }

    private void updateDVDById(int id, DVD dvd) throws Exception {
        try {
            statement = con.createStatement();
            String sql = "update dvd set state=" + (dvd.getState() == State.ON_SHELF ? 1 : 0) + " where id=" + dvd.getId();
            int code = statement.executeUpdate(sql);
            if (code == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
    }

    @Override
    public DVD revert(int id) {
        return null;
    }

    @Override
    public DVD revert(int id, Reader reader) throws Exception {
        DVD dvd = null;
        Time time = null;
        try {
            dvd = getDVDById(id);
            if (dvd.getState() == State.ON_SHELF) {
                System.out.println("该书已归还!如果不正确,请联系管理员.");
                throw new Exception();
            }

            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from time where serial='" + dvd.getTime().getSerial() + "'");
            if (resultSet.next()) {
                date = new java.util.Date();
                time = new Time.Builder()
                        .setId(dvd.getTime().getId())
                        .setBorrowTime(dvd.getTime().getBorrowTime())
                        .setRevertTime(dateShort())
                        .setRenewTime(dvd.getTime().getRenewTime())
                        .setSerial(dvd.getTime().getSerial())
                        .build();
//                System.out.println(time.toString());
            }

            dvd.setTime(time);
            assert time != null;
            // update time to table ,for reader time
            updateTimeBySerial(time.getSerial(), time);
            // remove from reader_dvd_list,passing from lab
            removeDVDFromReaderListByReaderId(reader.getId(), dvd);
            // set dvd not_avail to on_shelf
            updateDVDById(dvd.getId(), dvd);
        } catch (SQLException throwables) {
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        }
        return dvd;
    }

    @Override
    public void renew(int id, Time time) {

    }

    @Override
    public void addReader(Reader reader) {
        try {
            statement = con.createStatement();
            statement.executeUpdate("insert into reader values('" + reader.getName() + "'," + reader.getId() + ",'" + reader.getDvd_list_id() + "')");
            insertNewReaderListByIdFromReader(reader.getId());
        } catch (SQLException throwables) {
            System.out.println("失败,请重试或联系管理员!ERROR CODE: " + throwables.getErrorCode());
        } catch (Exception e) {
            System.out.println("失败,请重试或联系管理员!ERROR MSG: " + e.getMessage());
        }
    }

    @Override
    public Reader getReader(int id) {
        Reader reader = null;
        ResultSet set = null;
        try {
            statement = con.createStatement();
            set = statement.executeQuery("select * from reader where id=" + id);
            if (set.next()) {
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
    public DVD borrow(int id) {
        return null;
    }

    @Override
    public void insertNewTimeWhenAddDVD(Integer id, String serial) throws Exception {
        try {
            statement = con.createStatement();
            statement.executeUpdate("insert into time values('" + id + "','','','','" + serial + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<DVD> getAllReaderOwnDVDListByReader(Reader reader) throws Exception {
        ArrayList<DVD> own = null;
        int id_1, id_2, id_3;
        try {
            statement = con.createStatement();
            ResultSet set = statement.executeQuery("select * from reader_list where reader_id=" + reader.getId());
            if (set.next()) {
                own = new ArrayList<DVD>();
                id_1 = set.getInt(2);
                id_2 = set.getInt(3);
                id_3 = set.getInt(4);

                own.add(getDVDById(id_1));
                own.add(getDVDById(id_2));
                own.add(getDVDById(id_3));

                reader.setOwn(own);
                return own;
            } else
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (own == null)
            throw new Exception();
        return own;
    }

    @Override
    public void addDVD(DVD dvd) {
        try {
            statement = con.createStatement();
            statement.executeUpdate("insert into dvd values(" + dvd.getId() + ",'" + dvd.getTime().getSerial() + "'," + (dvd.getState() == State.NOT_AVAI ? 1 : 0) + ",'" + dvd.getName() + "')");
            insertNewTimeWhenAddDVD(dvd.getTime().getId(), dvd.getTime().getSerial());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dvd;
    }

    @Override
    public void removeReaderById(Integer id) throws Exception {
        try {
            statement = con.createStatement();
            int deleted = statement.executeUpdate("delete from reader where id=" + id);
            if (deleted == 0)
                throw new Exception();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        ArrayList<Reader> readers = new ArrayList<Reader>();
        ;
        ResultSet rt2 = null;
        try {
            statement = con.createStatement();
            rt2 = statement.executeQuery("select * from reader");
            while (rt2.next()) {
                readers.add(new Reader(rt2.getString(1), rt2.getInt(2), rt2.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rt2 != null;
            rt2.close();
        }
        return readers;
    }

    private String dateShort() {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
    }

}
