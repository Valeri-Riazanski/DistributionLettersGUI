package com.val.riazanski;

import javafx.scene.chart.XYChart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Model implements Observable {
    //fields
    private final ArrayList<Observer> observers; // список слушателей
    private static String op = "unsort"; // операция
    private static String inText = ""; // входной текст
    private final String result = ""; // результат
    private String distribution = "";
    private String distributionChar = "";
    private String distributionValue = "";
    private static XYChart.Series<Number,String> dataUnsort = new XYChart.Series<Number, String>();
    private static XYChart.Series<Number,String> dataSortKey = new XYChart.Series<Number, String>();
    private static XYChart.Series<Number,String> dataSortValue = new XYChart.Series<Number, String>();

    // constructors
    Model() {
        observers = new ArrayList<>();
    }

    // регистрация слушателя
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    // уведомление слушателей
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.notification(message);
        }
    }

    //set operation
    void setOp(String s) {
        op = s;
        notifyObservers("op");
    }

    //set number 1
    static void setInText(String s) {
        inText = s;
    }

    //calculate result
    void go() {
        //result = inText;
        notifyObservers("go");
    }

    //get result
    String getResult() {
        return result;
    }

    String getOp() {
        return op;
    }

    //get distribution
    String getDistribution() {
        return this.distribution;
    }
    String getDistributionChar() {
        return this.distributionChar;
    }
    String getDistributionValue() {
        return this.distributionValue;
    }
    static XYChart.Series<Number,String> getDataUnsort(){
        return dataUnsort;
    }
    XYChart.Series<Number,String> getDataSortValue(){
        return dataSortValue;
    }
    XYChart.Series<Number,String> getDataSortKey(){
        return dataSortKey;
    }
    void calcFrequencyDistribution(){
        String text = inText.toLowerCase();
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
        dataUnsort = dU;
        this.distribution = distributionBuilder.toString();
        System.out.println("несортированный лист" + '\n' + distribution);

        //лист сортированный по буквам
        XYChart.Series<Number,String> dK = new XYChart.Series<Number, String>();
        StringBuilder distributionBuilderChar = new StringBuilder();
        entries.sort((o1, o2) -> Character.compare(o1.getKey(), o2.getKey()));
        for(Map.Entry<Character, Integer> entry : entries){
            distributionBuilderChar.append(entry.getKey());
            distributionBuilderChar.append(entry.getValue());
            distributionBuilderChar.append(" ");
            dK.getData().add(new XYChart.Data<Number,String>(entry.getValue(),entry.getKey().toString()));
        }
        dataSortKey = dK;
        this.distributionChar = distributionBuilderChar.toString();
        System.out.println("лист сортированный по буквам " + '\n' + this.distributionChar);


        //лист сортированный по частоте
        XYChart.Series<Number,String> dV = new XYChart.Series<Number, String>();
        StringBuilder distributionBuilderValue = new StringBuilder();
        entries.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        for(Map.Entry<Character, Integer> entry : entries){
            distributionBuilderValue.append(entry.getKey());
            distributionBuilderValue.append(entry.getValue());
            distributionBuilderValue.append(" ");
            dV.getData().add(new XYChart.Data<Number,String>(entry.getValue(),entry.getKey().toString()));
        }
        dataSortValue = dV;
        this.distributionValue = distributionBuilderValue.toString();
        System.out.println("лист сортированный по частоте " + '\n' + this.distributionValue);

    }
}
