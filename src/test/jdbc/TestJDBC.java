package jdbc;

import god.hu.db.MDBOperator;
import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;
import god.hu.usage.abs.State;
import god.hu.usage.tool.SerialNumberGenerator;
import god.hu.usage.tool.cli.LabPrinter;
import god.hu.usage.tool.cli.TableRender;
import org.junit.Test;

import java.sql.*;

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
        MDBOperator operator = new MDBOperator();
        render.setShowVerticalLines(true);
        render.setHeaders("ID", "TIME", "STATE", "NAME");

        try {
            operator.updateTimeBySerial("JKKJDF", new Time.Builder().setBorrowTime("NICEPIE").setRevertTime("JKDJFDS").build());
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
            while (rs.next()) {
                render.addRow(rs.getString(1), rs.getString(2), String.valueOf(rs.getString(3) == "0" ? State.ON_SHELF : State.NOT_AVAI), rs.getString(4));
            }
            render.print();
            //step5 close the connection object
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testMysql() {
        TableRender render = new TableRender();
        MDBOperator operator = new MDBOperator();
        render.setShowVerticalLines(true);
        render.setHeaders("ID", "TIME", "STATE", "NAME");
        try {
            render.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTemp() {
        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i <= 20; i++)
            System.out.println(new SerialNumberGenerator().getNumber());
    }

    public String insert() {
        return "insert into dvd values ('10','nilajsd','2','TokyoHot')";
    }
}

