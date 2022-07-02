package edu.najah;

import javafx.scene.control.DatePicker;

public class User {
    private String name;
    private String pass;
    private String confpass;
    private String email;
    private String number;
    private DatePicker birthdate;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfpass() {
        return confpass;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }

    public User(String name, String number, String email, DatePicker birthdate, String pass, String confpass) {
        this.name = name;
        this.pass = pass;
        this.confpass = confpass;
        this.email = email;
        this.number = number;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DatePicker getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DatePicker birthdate) {
        this.birthdate = birthdate;
    }
}

