import java.sql.*;

public class conn {
    Connection con;
    Statement stmt;
    //    public conn(){
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            System.out.println("Driver Installed");
//            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Opponeo7");
//            System.out.println("Connection Established");
//            stmt = con.createStatement();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    public conn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Installed");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
            System.out.println("Connection Established");
            stmt = con.createStatement();
        }
        catch (SQLException throwables) {
//            throwables.printStackTrace();
            System.out.println("SQL Exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
    }

    public static void main(String[] args) {
        new conn();
    }
}
