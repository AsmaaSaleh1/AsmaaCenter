package edu.najah;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Serv {

    private String serNum;
    private String serDur;
    private String a;
    private String b;
private Button button;
private ImageView imageView;

    public Serv(String serNum, String a, String serDur, String b) {
        this.serNum = serNum;
        this.serDur = serDur;
        this.a = a;
        this.b = b;
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


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getSerDur() {
        return serDur;
    }

    public void setSerDur(String serDur) {
        this.serDur = serDur;
    }

}
