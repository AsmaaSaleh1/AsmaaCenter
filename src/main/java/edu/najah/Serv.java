package edu.najah;

public class Serv {

    private String serNum;
    private String serDur;
    private String a;
    private String b;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private Department department;

    public Serv(String serNum, String a, String serDur, String b,Department department) {
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

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }


    public String getSerDur() {
        return serDur;
    }

    public void setSerDur(String serDur) {
        this.serDur = serDur;
    }

    @Override
    public String toString() {
        return a       ;
    }

}
