package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] desk) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (j < 7 && desk[i + 2][j + 1] == 1 && desk[i][j] == 1) {
                    return false;
                }
                if (j < 6 && desk[i + 1][j + 2] == 1 && desk[i][j] == 1) {
                    return false;
                }
                if (j > 0 && desk[i + 2][j - 1] == 1 && desk[i][j] == 1) {
                    return false;
                }
                if (j > 1 && desk[i + 1][j - 2] == 1 && desk[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
