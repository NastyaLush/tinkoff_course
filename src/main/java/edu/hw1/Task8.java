package edu.hw1;

public class Task8 {
    private static final int COUNT_OF_SQUARE_ON_DESK = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] desk) {
        for (int i = 0; i < COUNT_OF_SQUARE_ON_DESK - 2; i++) {
            for (int j = 0; j < COUNT_OF_SQUARE_ON_DESK; j++) {
                if (j < COUNT_OF_SQUARE_ON_DESK - 1 && desk[i + 2][j + 1] == 1 && desk[i][j] == 1
                    || j < COUNT_OF_SQUARE_ON_DESK - 2 && desk[i + 1][j + 2] == 1 && desk[i][j] == 1
                    || j > 0 && desk[i + 2][j - 1] == 1 && desk[i][j] == 1
                    || j > 1 && desk[i + 1][j - 2] == 1 && desk[i][j] == 1) {
                    return false;
                }

            }
        }
        return true;
    }
}
