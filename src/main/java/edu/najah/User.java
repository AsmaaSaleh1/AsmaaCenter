package edu.najah;

public class User {
    private String name;
    private String pass;
    private String confpass;
    private String email;
    private String number;
    private String birthdate;

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

    public User(String name, String number, String email, String birthdate, String pass, String confpass) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
