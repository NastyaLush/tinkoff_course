package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.task19.Task19;
import edu.hw4.task19.TypeException;
import edu.hw4.task19.ValidationError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task19Test {

    @Test
    public void getAnimalsThatHaveErrors_shouldReturnAnimalsWithVelidationErrors() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        animals.add(new Animal("dogd", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        animals.add(new Animal("dog", null, null, 0, Integer.MIN_VALUE, -1, false));
        animals.add(new Animal(null, Animal.Type.BIRD, Animal.Sex.F, 1, 77, 8, false));
        Map<String, Set<ValidationError>>
                expectedMap = new HashMap<>();
        HashSet<ValidationError> set = new HashSet<>();
        set.add(new ValidationError(TypeException.NON_UNIQUE, animals.get(0)
                                                                     .getNameTitle()));
        set.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animals.get(0)
                                                                           .getWeightTitle()));
        set.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animals.get(0)
                                                                           .getAgeTitle()));
        set.add(new ValidationError(TypeException.NEGATIVE_OR_ZERO, animals.get(0)
                                                                           .getHeightTitle()));
        set.add(new ValidationError(TypeException.NULL, animals.get(0)
                                                               .getSexTitle()));
        set.add(new ValidationError(TypeException.NULL, animals.get(0)
                                                               .getTypeTitle()));
        expectedMap.put("dog", set);
        set = new HashSet<>();
        set.add(new ValidationError(TypeException.NULL, animals.get(0)
                                                               .getNameTitle()));
        expectedMap.put(null, set);

        Map<String, Set<ValidationError>> actualMap = new Task19().getAnimalsThatHaveErrors(animals);

        expectedMap.forEach((s, validationErrors) -> assertEquals(validationErrors, actualMap.get(s)));
    }

    @Test
    public void getAnimalsThatHaveErrors_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        Map<String, Set<ValidationError>>
                expectedMap = new HashMap<>();


        Map<String, Set<ValidationError>> actualMap = new Task19().getAnimalsThatHaveErrors(animals);

        assertTrue(expectedMap.equals(actualMap));
    }

}
