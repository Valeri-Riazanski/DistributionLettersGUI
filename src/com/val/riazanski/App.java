package com.val.riazanski;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {
    @FXML private CategoryAxis xAxis= new CategoryAxis();
    @FXML private NumberAxis yAxis= new NumberAxis();
    @FXML private BarChart<Number,String> barChart = new BarChart<Number,String>(yAxis, xAxis);


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainform.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Listener Calculator");

        barChart.getData().add(calcFrequencyDistribution("эжджафждыджаэфукафуафжджаоэфжыу"));
        barChart.setLegendVisible(false);
        VBox vbox = new VBox(barChart);
        primaryStage.setScene(new Scene(vbox));

        primaryStage.setScene(scene); //если закоментить эту строку, то получим картинку с горизонтальным чартбаром.
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    XYChart.Series<Number,String> calcFrequencyDistribution(String text){
        text = text.toLowerCase();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            // ё идёт отдельно от а-я
            if((ch >= 'а' && ch <= 'я') || ch == 'ё'){
                map.merge(ch,1,Integer::sum);
            }
        }
        // несортированный лист
        XYChart.Series<Number,String> dU = new XYChart.Series<Number, String>();
        StringBuilder distributionBuilder = new StringBuilder();
        ArrayList<HashMap.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        for(Map.Entry<Character, Integer> entry : entries){
            distributionBuilder.append(entry.getKey());
            distributionBuilder.append(entry.getValue());
            distributionBuilder.append(" ");
            dU.getData().add(new XYChart.Data<Number,String>(entry.getValue(),entry.getKey().toString()));
        }
       return dU;
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}