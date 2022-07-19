package edu.najah;

import java.time.LocalDate;

public class Reservation {
    private User user;
    private Appo appo;
    private int serNum;
    private int totPrice;
    private LocalDate date;
    private String time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Reservation(User user, Appo appo, int serNum, int totPrice) {
        this.user = user;
        this.appo = appo;
        this.serNum = serNum;
        this.totPrice = totPrice;
       this. id=user.getId();
       this.name=user.getName();
       this.date=appo.getAppoDate();
      // this.time=appo.getTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appo getAppo() {
        return appo;
    }

    public void setAppo(Appo appo) {
        this.appo = appo;
    }

    public int getSerNum() {
        return serNum;
    }

    public void setSerNum(int serNum) {
        this.serNum = serNum;
    }

    public int getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(int totPrice) {
        this.totPrice = totPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "user=" + user +
                ", appo=" + appo +
                ", serNum=" + serNum +
                ", totPrice=" + totPrice +
                '}';
    }
}
