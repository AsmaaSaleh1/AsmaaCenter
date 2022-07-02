package edu.najah;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Serv {
    private String  serName;
    private String serNum;
    private String serDur;
    private String sePrice;
private Button button;
private ImageView imageView;
    public String getSerName() {
        return serName;
    }

    public String serNameProperty() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName=serName;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }
    public Serv(String serName, String serNum, String serDur, String sePrice) {
        this.serName = serName;
        this.serNum = serNum;
        this.serDur = serDur;
        this.sePrice = sePrice;

        this.imageView=new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\female.png"));
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
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

    public String getSePrice() {
        return sePrice;
    }

    public void setSePrice(String sePrice) {
        this.sePrice = sePrice;
    }

}
