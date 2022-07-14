package edu.najah;

import java.time.LocalDate;

public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private String pass;
    private String confpass;
    private String email;
    private String number;
    private LocalDate birthdate;

    public User(int i, String hiba, String dgfhcv, String s, LocalDate datePicker,String pass) {
        id=i;
        name=hiba;
        email=dgfhcv;
        number=s;
        birthdate=datePicker;
        this.pass=pass;
    }

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

    public User(String name, String number, String email, LocalDate birthdate, String pass, String confpass) {
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}

