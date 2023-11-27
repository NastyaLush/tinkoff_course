package edu.hw4.task19;

import edu.hw4.given.Animal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task19 {

    private Task19() {
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = validateAnimal(animal, animals);
            if (!errors.isEmpty()) {
                errorsMap.put(animal.name(), errors);
            }
        }
        return errorsMap;
    }

    private static Set<ValidationError> validateAnimal(Animal animal, List<Animal> animals) {
        Set<ValidationError> errors = new HashSet<>();
        if (isNegativeOrZero(animal.age())) {
            errors.add(new ValidationError(ErrorType.NEGATIVE_OR_ZERO, animal.getAgeTitle()));
        }
        if (isNegativeOrZero(animal.weight())) {
            errors.add(new ValidationError(ErrorType.NEGATIVE_OR_ZERO, animal.getWeightTitle()));
        }
        if (isNegativeOrZero(animal.height())) {
            errors.add(new ValidationError(ErrorType.NEGATIVE_OR_ZERO, animal.getHeightTitle()));
        }
        if (isNull(animal.name())) {
            errors.add(new ValidationError(ErrorType.NULL, animal.getNameTitle()));
        }
        if (isNull(animal.type())) {
            errors.add(new ValidationError(ErrorType.NULL, animal.getTypeTitle()));
        }
        if (isNull(animal.sex())) {
            errors.add(new ValidationError(ErrorType.NULL, animal.getSexTitle()));
        }
        if (!isUnique(animal.name(), animals.stream()
                                            .map(Animal::name)
                                            .toList())) {
            errors.add(new ValidationError(ErrorType.NON_UNIQUE, animal.getNameTitle()));
        }
        return errors;
    }


    private static boolean isNegativeOrZero(int value) {
        return value <= 0;
    }

    private static boolean isNull(Object value) {
        return value == null;
    }

    private static <T> boolean isUnique(T animal, List<T> animals) {
        return animals.stream()
                      .filter(name -> name == animal)
                      .count() == 1;
    }

}
