package edu.najah;

public class Serv {

    private int serNum;
    private int serDur;
    private String a;
    private int b;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private Department department;

    public Serv(int serNum, String a, int serDur, int b,Department department) {
        this.serNum = serNum;
        this.serDur = serDur;
        this.a = a;
        this.b = b;
        this.department=department;
    }

    public Department getDepName() {
        return department;
    }

    public void setDepName(Department depName) {
        this.department = depName;
    }

    public String getA() {
        return a;
    }

    public void setA(String  a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getSerNum() {
        return serNum;
    }

    public void setSerNum(int serNum) {
        this.serNum = serNum;
    }


    public int getSerDur() {
        return serDur;
    }

    public void setSerDur(int serDur) {
        this.serDur = serDur;
    }

    @Override
    public String toString() {
        return a       ;
    }

}
