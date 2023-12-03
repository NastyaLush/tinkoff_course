package edu.hw4.given;

public record Animal(String name, Type type, Sex sex, int age, int height, int weight, boolean bites) {

    public static final Integer DEFAULT_PAWS_OF_DOG_AND_CAT = 4;
    public static final Integer DEFAULT_PAWS_OF_BIRD = 2;
    public static final Integer DEFAULT_PAWS_OF_FISH = 0;
    public static final Integer DEFAULT_PAWS_OF_SPIDER = 8;

    public int paws() {
        return switch (type) {
            case CAT, DOG -> DEFAULT_PAWS_OF_DOG_AND_CAT;
            case BIRD -> DEFAULT_PAWS_OF_BIRD;
            case FISH -> DEFAULT_PAWS_OF_FISH;
            case SPIDER -> DEFAULT_PAWS_OF_SPIDER;
        };
    }

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    public String getNameTitle() {
        return "name";
    }

    public String getAgeTitle() {
        return "age";
    }

    public String getSexTitle() {
        return "sex";
    }

    public String getTypeTitle() {
        return "type";
    }

    public String getWeightTitle() {
        return "weight";
    }

    public String getHeightTitle() {
        return "width";
    }

    public String getBitesTitle() {
        return "bites";
    }
}
