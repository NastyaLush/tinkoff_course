package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    @Override
    public String toString() {
        return "CallingInfo{" + "className='" + className + '\'' + ", methodName='" + methodName + '\'' + '}';
    }
}
