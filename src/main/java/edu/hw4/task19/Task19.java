package edu.hw4.task19;

import edu.hw4.given.Animal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task19 {

    private Set<ValidationError> errors;

    public Map<String, Set<ValidationError>> getAnimalsThatHaveErrors(List<Animal> animals) {
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        for (Animal animal : animals) {
            if (isHasErrors(animal, animals)) {
                errorsMap.put(animal.name(), getErrors());
            }
        }
        return errorsMap;
    }

    private boolean isHasErrors(Animal animal, List<Animal> animals) {
        errors = new HashSet<>();
        if (isNegativeOrZero(animal.age())) {
            errors.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animal.getAgeTitle()));
        }
        if (isNegativeOrZero(animal.weight())) {
            errors.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animal.getWeightTitle()));
        }
        if (isNegativeOrZero(animal.height())) {
            errors.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animal.getHeightTitle()));
        }
        if (isNUll(animal.name())) {
            errors.add(new ValidationError(TypeException.NULL, animal.getNameTitle()));
        }
        if (isNUll(animal.type())) {
            errors.add(new ValidationError(TypeException.NULL, animal.getTypeTitle()));
        }
        if (isNUll(animal.sex())) {
            errors.add(new ValidationError(TypeException.NULL, animal.getSexTitle()));
        }
        if (!isUnique(animal.name(), animals.stream()
                                            .map(Animal::name)
                                            .toList())) {
            errors.add(new ValidationError(TypeException.NON_UNIQUE, animal.getNameTitle()));
        }
        return errors.size() > 0;
    }

    public Set<ValidationError> getErrors() {
        return errors;
    }


    private boolean isNegativeOrZero(int value) {
        return value <= 0;
    }

    private boolean isNUll(Object value) {
        return value == null;
    }

    private <T> boolean isUnique(T animal, List<T> animals) {
        return animals.stream()
                      .filter(name -> name == animal)
                      .count() == 1;
    }

}
