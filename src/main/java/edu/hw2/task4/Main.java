package edu.hw2.task4;

public class Main {
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        return new CallingInfo(className, methodName);
    }

    public static void main(String[] args) {
        callingInfo();
    }

    private Main() {
    }
}
