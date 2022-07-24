package edu.najah;

import java.time.LocalDate;

public class Emp {
    private int id;

    private String email;
    private final String mobNum;
    private final String city;
    private final String x;
    private String y;

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    private final String street;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public Emp(int id, String x, String y, LocalDate birthdate, LocalDate startDate, String email, String mobNum, String city, String street, int salary, String depNum) {
        this.id = id;
        this.email = email;
        this.mobNum = mobNum;
        this.city = city;
        this.x = x;
        this.y = y;
        this.street = street;
        this.salary = salary;
        this.depNum = depNum;
        this.birthdate = birthdate;
        this.startDate = startDate;
        this.name=x+" "+y;
    }

    private final int salary;

    public String getDepNum() {
        return depNum;
    }

    private final String depNum;
    private LocalDate birthdate;
    private final LocalDate startDate;
private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobNum() {
        return mobNum;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getSalary() {
        return salary;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", fName='" + x + '\'' +
                ", lName='" + y + '\'' +
                ", email='" + email + '\'' +
                ", mobNum='" + mobNum + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", salary=" + salary +
                ", depNum=" + depNum +
                ", birthdate=" + birthdate +
                ", startDate=" + startDate +
                '}';
    }
}
