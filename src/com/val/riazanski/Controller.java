package com.val.riazanski;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller<xAxis> extends View {
    // fields
    @FXML private  TextField tf1;
    @FXML private  Button btnKey = new Button();
    @FXML private  Button btnValue = new Button();
    @FXML private  Button btnUnsort = new Button();
    @FXML private  Button btnGo = new Button();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // actions BtnValue
    @FXML public void onActionBtnValue() {
        model.setOp("value");
        btnValue.setStyle("-fx-border-color: blue");
        btnKey.setStyle("-fx-border-color: grey");
        btnUnsort.setStyle("-fx-border-color: grey");
        btnGo.setStyle("-fx-border-color: grey");

    }

    //actions BtnKey
    @FXML public void onActionBtnKey() {
        model.setOp("key");
        btnKey.setStyle("-fx-border-color: red");
        btnValue.setStyle("-fx-border-color: grey");
        btnUnsort.setStyle("-fx-border-color: grey");
        btnGo.setStyle("-fx-border-color: grey");
    }

    //actions BtnUnsort
    @FXML public void onActionBtnUnsort() {
        model.setOp("unsort");
        btnUnsort.setStyle("-fx-border-color: yellow");
        btnKey.setStyle("-fx-border-color: grey");
        btnValue.setStyle("-fx-border-color: grey");
        btnGo.setStyle("-fx-border-color: grey");
    }

    // actions BtnGo
    @FXML public void onActionBtnGo() {
        this.go();
        btnGo.setStyle("-fx-border-color: green");
    }

    // get result
    private void go() {
        model.setInText(tf1.getText());
        model.calcFrequencyDistribution();
        model.go();
    }
}

