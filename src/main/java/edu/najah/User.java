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
    private final String pass;
    private String email;
    private String number;
    private LocalDate birthdate;

    public String getUsername() {
        return username;
    }

    String username;
    public User(String name, String number, String email, LocalDate birthdate, String pass, String confpass,String username) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.number = number;
        this.birthdate = birthdate;
        this.username=username;
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

    public User(String name, String number, String email, LocalDate birthdate, String pass, String confpass) {
        this.name = name;
        this.pass = pass;
        this.username = confpass;
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

    @Override
    public String toString() {
        return
                "username='" + username;
    }
}

