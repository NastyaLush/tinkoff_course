package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        String binN = Integer.toBinaryString(n);
        int offset = shift % binN.length();
        return Integer.parseInt(binN.substring(offset) + binN.substring(0, offset), 2);
    }

    public static int rotateRight(int n, int shift) {
        String binN = Integer.toBinaryString(n);
        int offset = shift % binN.length();
        return rotateLeft(n, binN.length() - offset);
    }
}
