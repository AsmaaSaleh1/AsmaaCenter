package edu.najah;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appo {
    private final int num;

    public int getTotal() {
        return total;
    }

    private  int total;
    private final LocalDate appoDate;
    private LocalTime time;
    private User user;

    public void setNumOfSer(int numOfSer) {
    }
    private int numOfSer;
public  int getNumOfSer(){return numOfSer;}
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



    private final String un;

    public void setTotal(int total) {
    }

    public Appo(int num, LocalDate appoDate, LocalTime time, User user) {
        this.num = num;
        this.appoDate = appoDate;
        this.time = time;
        this.user = user;
        un= getUser().username;
        name=user.getName();
    }

    public int getNum() {
        return num;
    }

    public LocalDate getAppoDate() {
        return appoDate;
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
