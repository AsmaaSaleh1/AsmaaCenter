package edu.najah;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appo {
    private int num;
    private LocalDate appoDate;
    private LocalTime time;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    private String un;
    public Appo(int num, LocalDate appoDate, LocalTime time, User user) {
        this.num = num;
        this.appoDate = appoDate;
        this.time = time;
        this.user = user;
        un= getUser().username;
        name=user.getName();
    }
    public Appo(int num, LocalDate appoDate, LocalTime time) {
        this.num = num;
        this.appoDate = appoDate;
        this.time = time;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getAppoDate() {
        return appoDate;
    }

    public void setAppoDate(LocalDate appoDate) {
        this.appoDate = appoDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
