package test.jdbc;

import god.hu.db.DBOperator;
import god.hu.model.DVD;
import god.hu.model.Time;
import god.hu.usage.abs.State;
import god.hu.usage.tool.SerialNumberGenerator;
import god.hu.usage.tool.cli.TableRender;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestJDBC {
    private SerialNumberGenerator generator = new SerialNumberGenerator(7);

    @Test
    public void testConnect() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:god", "scott", "tiger");

//step3 create the statement object
            Statement stmt = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(insert());
//step4 execute query
            // ResultSet rs = stmt.executeQuery("select * from app_test");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4));
//step5 close the connection object
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @Test
    public void testPrint() {
        TableRender render = new TableRender();
        DBOperator operator = new DBOperator();
        render.setShowVerticalLines(true);
        render.setHeaders("ID", "TIME", "STATE", "NAME");
//        operator.addDVD(new DVD.Builder()
//                .setID(111)
//                .setTime(new Time.Builder().setSerial(new SerialNumberGenerator(7).generate()).build())
//                .setState(State.ON_SHELF)
//                .setName("nicepie")
//                .build()
//        );
        try {
            System.out.println(operator.selectCountByName("TokyoHot")+"<---");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:god", "scott", "tiger");
//step3 create the statement object
            Statement stmt = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement("select distinct name from dvd ");
//step4 execute query
            // ResultSet rs = stmt.executeQuery("select * from app_test");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                render.addRow(rs.getString(1),rs.getString(2),String.valueOf(rs.getString(3)=="0"?State.ON_SHELF:State.NOT_AVAI),rs.getString(4));
            }
            render.print();
//step5 close the connection object
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void testOracleConnect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           // Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.113.84:1521:god", "scott", "tiger");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@192.168.113.73:1521:orcl","scott","1622");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insert() {
        return "insert into dvd values ('10','nilajsd','2','TokyoHot')";
    }
}

