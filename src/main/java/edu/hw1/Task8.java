package edu.hw1;

public class Task8 {

    private static final int COUNT_OF_SQUARE_ON_DESK = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] desk) {
        for (int i = 0; i < COUNT_OF_SQUARE_ON_DESK - 2; i++) {
            for (int j = 0; j < COUNT_OF_SQUARE_ON_DESK; j++) {
                if (checkKnights(desk, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkKnights(int[][] desk, int i, int j) {
        return checkKnightLeftUp(desk, i, j)
            || checkKnightRightUp(desk, i, j)
            || checkKnightLeftDown(desk, i, j)
            || checkKnightRightDown(desk, i, j);
    }

    private static boolean checkKnightRightDown(int[][] desk, int i, int j) {
        return j < COUNT_OF_SQUARE_ON_DESK - 1 && desk[i + 2][j + 1] == 1 && desk[i][j] == 1;
    }

    private static boolean checkKnightLeftDown(int[][] desk, int i, int j) {
        return j > 0 && desk[i + 2][j - 1] == 1 && desk[i][j] == 1;
    }

    private static boolean checkKnightRightUp(int[][] desk, int i, int j) {
        return j < COUNT_OF_SQUARE_ON_DESK - 2 && desk[i + 1][j + 2] == 1 && desk[i][j] == 1;
    }

    private static boolean checkKnightLeftUp(int[][] desk, int i, int j) {
        return j > 1 && desk[i + 1][j - 2] == 1 && desk[i][j] == 1;
    }
}
