package edu.hw2.task4;

public class Main {
    public static void main(String[] args) {
        try {
            new CallingInfo("className", "methodName");
            throw new Exception(new CallingInfo("className", "methodName").className());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
