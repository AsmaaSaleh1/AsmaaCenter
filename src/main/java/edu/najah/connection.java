package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class connection {

    public static Connection connect() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
            System.out.println("Connect success");
            return connection;
        }
catch (SQLException e){
    System.out.println(e);
    return null;
}
    }
    public static ObservableList<Emp> getEmployee() {
        Connection con = connect();
        ObservableList<Emp> list = FXCollections.observableArrayList();
        try {
            Statement statement = con.createStatement();
            String q = "select eid, fname, lname,email,mobilenum,salary,city,street, birthdate, startdate ,dname from employee, department where dnum=department.dnumber order by(eid)";
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
               list.add(new Emp(rs.getInt("eid"), rs.getString("fname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getString("dname")));

            }
            con.commit();
            con.close();

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public static ObservableList<Department> getDepartment() {
        Connection con = connect();
        ObservableList<Department> list = FXCollections.observableArrayList();
        try {
            Statement statement = con.createStatement();
            String q = "select dnumber ,dname from department";
            ResultSet rs = statement.executeQuery(q);

            while (rs.next()) {
               list.add(new Department(rs.getInt(1), rs.getString(2)));

            }
            con.commit();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public static ObservableList<Serv> getSrevices() {
        Connection con = connect();
        ObservableList<Serv> list = FXCollections.observableArrayList();
        try {
            Statement statement = con.createStatement();
            String q = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum order by sid";
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                list.add(new Serv(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),new Department(rs.getInt(5), rs.getString(6))));
            }
            con.commit();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public static ObservableList<User> getCustomer(){
        Connection con = connect();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            Statement statement = con.createStatement();
            String q="select * FROM customer" ;
            statement.executeQuery(q);
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                list.add(new User(rs.getString(2),rs.getString(5), rs.getString(4),rs.getDate(3).toLocalDate(),rs.getString(6),rs.getString(1) ));
                System.out.println("y");
            }

            con.close();
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  static ObservableList<Appo> getAllApo(){
        Connection con=connect();
        ObservableList<Appo> list = FXCollections.observableArrayList();
        try {
            Statement statement = con.createStatement();
            String q="select * FROM appo join customer on customer.user_name=appo.custpk order by apponum";
            statement.executeQuery(q);
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                list.add(new Appo(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getTime(3).toLocalTime(),
                        new User(rs.getString("FNAME"),rs.getString("email"), rs.getString("MOB_NUM"),rs.getDate("BIRTHDATE").toLocalDate(),rs.getString("CPASSWORD"),rs.getString("USER_NAME") ) ));
                System.out.println("y");
            }

            con.close();
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
