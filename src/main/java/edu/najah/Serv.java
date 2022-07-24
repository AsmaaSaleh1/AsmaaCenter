package edu.najah;

public class Serv {

    private final int serNum;
    private final int serDur;
    private String a;
    private final int b;

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

    public String getA() {
        return a;
    }

    public void setA(String  a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public int getSerNum() {
        return serNum;
    }


    public int getSerDur() {
        return serDur;
    }

    @Override
    public String toString() {
        return a       ;
    }

}
