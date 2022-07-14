package edu.najah;

public class Department {
    private int num;
    private String name;

    public Department(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return      name  ;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
