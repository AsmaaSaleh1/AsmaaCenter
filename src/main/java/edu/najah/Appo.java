package edu.najah;

import java.time.LocalDate;

public class Appo {
    private int num;
    private LocalDate appoDate;
    private String time;
    private User user;

    public Appo(int num, LocalDate appoDate, String time, User user) {
        this.num = num;
        this.appoDate = appoDate;
        this.time = time;
        this.user = user;
    }
    public Appo(int num, LocalDate appoDate, String time) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
