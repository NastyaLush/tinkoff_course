package edu.hw4;

import edu.hw4.given.Animal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task20 {

    public Map<String, String> animalsThatHaveErrorsWithPrettyPrint(List<Animal> animals) {
        Map<String, String> prettyErrorsMap = new HashMap<>();
        Map<String, Set<ValidationError>> errorsMap = new Task19().animalsThatHaveErrors(animals);

        errorsMap.forEach((animalName, validationErrors) -> {
            StringBuilder errorFields = new StringBuilder();
            for (String nameField : validationErrors.stream()
                                                    .map(ValidationError::getNameField)
                                                    .collect(Collectors.toSet())) {
                errorFields.append(nameField)
                           .append(", ");
            }
            prettyErrorsMap.put(animalName, errorFields.toString());

        });

        return prettyErrorsMap;
    }

}
