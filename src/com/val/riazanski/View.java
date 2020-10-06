package com.val.riazanski;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class View implements Observer {
    //fields
    @FXML private Label LabelResult;
    @FXML private Label LabelOp;
    @FXML private CategoryAxis xAxis= new CategoryAxis();
    @FXML private NumberAxis yAxis= new NumberAxis();
    @FXML private BarChart<Number,String> barChart = new BarChart<Number,String>(yAxis, xAxis);

    Model model = new Model();

    // constructors
    View() {
        model.registerObserver(this);
    }

    //methods
    @Override
    public void notification(String message) {
        switch (message) {
            case ("op") :
                this.displayOp(model.getOp());
                break;
            case ("go") :
                switch (model.getOp()) {
                    case ("unsort"):
                        this.displayLabel(model.getDistribution(),model.getDataUnsort());
                        break;
                    case ("key"):
                        this.displayLabel(model.getDistributionChar(),model.getDataSortKey());
                        break;
                    case ("value"):
                        this.displayLabel(model.getDistributionValue(),model.getDataSortValue());
                        break;
                }
        }
    }
    // display text to LabelResult
    private void displayLabel(String s, XYChart.Series<Number,String> d){
        LabelResult.setText("distribution: " + s);
        //barChart.getData().add(d);
        //barChart.setLegendVisible(false);
        //VBox vbox = new VBox(barChart);
        //Stage.setScene(new Scene(vbox, 500, 300));
    }
    // display operation to LabelOp
    private void displayOp(String s) {
        LabelOp.setText("sort by " + s);
    }
}