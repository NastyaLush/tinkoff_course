package edu.hw2.task4;

public class Main {

    private Main() {
    }

    public static CallingInfo callingInfo(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
