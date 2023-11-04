package edu.hw5.task8;

import java.util.regex.Pattern;

public class Task8 {

    public boolean isOddString(String input) {
        Pattern pattern = Pattern.compile("^(0|1)((00)|(01)|(10)|(11))*$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean isBeginsWith0AndOddOrBeginsWith1AndEven(String input) {
        Pattern pattern = Pattern.compile("^(0((00)|(01)|(10)|(11))*|1(0|1)((00)|(01)|(10)|(11))*)$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean isCountOfZerousMultiple3(String input) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)+$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean isNot11or111(String input) {
        Pattern pattern = Pattern.compile("^(?!1{2,3}$)[0|1]*$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean isOddDigitIs1(String input) {
        Pattern pattern = Pattern.compile("^1(0$|1$|(01)|(11)$)*$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean moreOrEqual2ZerousAndOneOrLessOne(String input) {
        Pattern pattern = Pattern.compile("^((0{2,}0*1{0,1}0*)|(0*1{0,1}0{2,}0*))$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean noConsecutive1(String input) {
        Pattern pattern = Pattern.compile("^0*(0*(10|1$)*)*$");
        return pattern.matcher(input)
                      .find();
    }

}
