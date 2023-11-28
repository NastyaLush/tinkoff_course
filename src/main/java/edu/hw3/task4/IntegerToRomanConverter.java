package edu.hw3.task4;

import java.util.HashMap;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class IntegerToRomanConverter {

    private static final int ONE_ARABIC = 1;
    private static final int FIVE_ARABIC = 5;
    private static final int TEN_ARABIC = 10;
    private static final int FIFTY_ARABIC = 50;
    private static final int HUNDRED_ARABIC = 100;
    private static final int FIVE_HUNDRED_ARABIC = 500;
    private static final int THOUSAND_ARABIC = 1000;
    private final int minAllowedDigit = 1;
    private final int maxAllowedDigit = 4999;
    private final int thousandsPosition = 1000;
    private final int hundredsPosition = 100;
    private final int tensPosition = 10;
    private final int onesPosition = 1;
    private final int notation = 10;
    private final int boundaryValueFive = 5;
    private final int boundaryValueFour = 4;
    private final int boundaryValueNine = 9;

    private final HashMap<Integer, String> arabicFigureToRoman;

    public IntegerToRomanConverter() {
        arabicFigureToRoman = new HashMap<>();
        arabicFigureToRoman.put(ONE_ARABIC, "I");
        arabicFigureToRoman.put(FIVE_ARABIC, "V");
        arabicFigureToRoman.put(TEN_ARABIC, "X");
        arabicFigureToRoman.put(FIFTY_ARABIC, "L");
        arabicFigureToRoman.put(HUNDRED_ARABIC, "C");
        arabicFigureToRoman.put(FIVE_HUNDRED_ARABIC, "D");
        arabicFigureToRoman.put(THOUSAND_ARABIC, "M");
    }

    public String convertToRoman(Integer number) {
        log.info("begin converting arabic to roman");

        if (number < minAllowedDigit || number > maxAllowedDigit) {
            throw new IllegalArgumentException();
        }
        Integer thousands = number / thousandsPosition;
        Integer hundreds = number / hundredsPosition % notation;
        Integer tens = number / tensPosition % notation;
        Integer ones = number % notation;

        log.info("finish converting arabic to roman");

        return thousandsToRoman(thousands)
                + numberPositionToRoman(hundreds, hundredsPosition)
                + numberPositionToRoman(tens, tensPosition)
                + numberPositionToRoman(ones, onesPosition);
    }

    private String getNTimesLetter(Integer countOfTimes, String letter) {
        Integer count = countOfTimes;
        String th = "";
        while (count-- > 0) {
            th += letter;
        }
        return th;
    }

    private String thousandsToRoman(int countOfThousands) {
        return getNTimesLetter(countOfThousands, arabicFigureToRoman.get(thousandsPosition));
    }

    private String convertNineInDifferentPositionToRoman(int numberPosition) {
        return arabicFigureToRoman.get(numberPosition)
                + arabicFigureToRoman.get(numberPosition * notation);
    }

    private String convertFourInDifferentPositionToRoman(int numberPosition) {
        return arabicFigureToRoman.get(numberPosition)
                + arabicFigureToRoman.get(numberPosition * boundaryValueFive);
    }

    private String convertDigitBetween5And9InDifferentPosition(int countOfNumberPosition, int numberPosition) {
        if (countOfNumberPosition == boundaryValueNine) {
            return convertNineInDifferentPositionToRoman(numberPosition);
        } else {
            return arabicFigureToRoman.get(boundaryValueFive * numberPosition)
                    + numberPositionToRoman(countOfNumberPosition - boundaryValueFive, numberPosition);
        }
    }

    private String convertDigitLessThan5InDifferentPosition(int countOfNumberPosition, int numberPosition) {
        if (countOfNumberPosition == boundaryValueFour) {
            return convertFourInDifferentPositionToRoman(numberPosition);
        } else {
            return getNTimesLetter(countOfNumberPosition, arabicFigureToRoman.get(numberPosition));
        }
    }

    private String numberPositionToRoman(int countOfNumberPosition, int numberPosition) {
        if (countOfNumberPosition == 0) {
            return "";
        }
        if (countOfNumberPosition >= boundaryValueFive) {
            return convertDigitBetween5And9InDifferentPosition(countOfNumberPosition, numberPosition);
        } else {
            return convertDigitLessThan5InDifferentPosition(countOfNumberPosition, numberPosition);
        }
    }

}
