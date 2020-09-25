package god.hu.db;

import java.sql.*;

public class SqlOperation {
    public Connection con;
    public Statement st;
    public String tablename = "BOOKTABLE";

    public boolean setConnect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:192.168.113.69:1521:god", "scott", "tiger");
            if (con.isClosed() || con == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnect() {
        return con;
    }

    public boolean close() {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setTableName(String str) {
        tablename = str;
    }

    public boolean updateLend_Return(String kn, int jie, int sheng) {
        if (con == null) {
            if (!setConnect()) return false;
        }
        try {
            st = con.createStatement();
            String sql = "update " + tablename + " set jnum =" + jie + " , snum = " + sheng + " where kn = " + kn;
            int count = st.executeUpdate(sql);
            if (count > 0) {
                System.out.println("Successful!");
            } else {
                System.out.println("Fail!");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBookNumber(String kn, int zhong) {
        if (con == null) {
            if (!setConnect()) return false;
        }
        try {
            st = con.createStatement();
            String sql = "update " + tablename + " set znum =" + zhong + " where kn = " + kn;
            int count = st.executeUpdate(sql);
            if (count > 0) {
                System.out.println("Successful!");
            } else {
                System.out.println("Fail!");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String kn) {
        if (con == null) {
            if (!setConnect()) return false;
        }
        try {
            st = con.createStatement();
            String sql = "delete from " + tablename + " where kn='" + kn + "'";
            int count = st.executeUpdate(sql);
            if (count > 0) {
                System.out.println("Successful!");
            } else {
                System.out.println("Fail!");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean insertbook(String[] ss) {
        if (con == null) {
            if (!setConnect()) return false;
        }
        try {
            st = con.createStatement();
            String sql = "insert into " + tablename + "(KN,BNAME,ZNUM,JNUM,SNUM) values( '" + ss[1] + "','" + ss[2] + "','" + ss[3] + "','" + ss[4] + "','" + ss[5] + "')";
            int count = st.executeUpdate(sql);
            if (count > 0) {
                System.out.println("Successful!");
            } else {
                System.out.println("Fail!");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ResultSet selectAll() {
        if (con == null) {
            if (!setConnect()) return null;
        }
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select  * from " + tablename;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("select Successful!");
            } else {
                System.out.println("select Fail!");
                return null;
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectBookAll(String kn) {
        if (con == null) {
            if (!setConnect()) return null;
        }
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select  * from " + tablename + " where kn='" + kn + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("select Successful!");
            } else {
                System.out.println("select Fail!");
                return null;
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[][] dealResultSet(ResultSet rs) {
        try {
            if (rs == null) {
                System.out.println("ResultSet is null!");
                return null;
            }
            rs.last();
            int row = rs.getRow();
            String[][] ss = new String[row][];
            rs.first();
            int rowcount = 0;
            do {
                ResultSetMetaData rsmd = rs.getMetaData();
                int column = rsmd.getColumnCount();
                ss[rowcount] = new String[column];
                for (int i = 0; i < column; i++) {
                    ss[rowcount][i] = rs.getString(i + 1);
                }
                rowcount++;
            } while (rs.next());
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showArray2(String[][] ss) {
        for (int i = 0; i < ss.length; i++) {
            for (int j = 0; j < ss[i].length; j++) {
                System.out.print(ss[i][j] + "  ");
            }
            System.out.println();
        }
    }

}

