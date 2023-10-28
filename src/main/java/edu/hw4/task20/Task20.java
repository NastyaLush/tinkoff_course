package edu.hw4.task20;

import edu.hw4.given.Animal;
import edu.hw4.task19.Task19;
import edu.hw4.task19.ValidationError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task20 {

    public Map<String, String> animalsThatHaveErrorsWithPrettyPrint(List<Animal> animals) {
        Map<String, String> prettyErrorsMap = new HashMap<>();
        Map<String, Set<ValidationError>> errorsMap = new Task19().getAnimalsThatHaveErrors(animals);

        errorsMap.forEach((animalName, validationErrors) -> {
            StringBuilder errorFields = new StringBuilder();
            for (String nameField : validationErrors.stream()
                                                    .map(ValidationError::getNameField)
                                                    .collect(Collectors.toSet())) {
                errorFields.append(nameField)
                           .append(", ");
            }
            errorFields.delete(errorFields.length() - 2, errorFields.length());
            prettyErrorsMap.put(animalName, errorFields.toString());

        });

        return prettyErrorsMap;
    }

}
