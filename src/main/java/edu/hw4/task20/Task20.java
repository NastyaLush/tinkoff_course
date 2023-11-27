package edu.hw4.task20;

import edu.hw4.given.Animal;
import edu.hw4.task19.Task19;
import edu.hw4.task19.ValidationError;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task20 {

    public Map<String, String> animalsThatHaveErrorsWithPrettyPrint(List<Animal> animals) {
        return Task19.validateAnimals(animals)
                     .entrySet()
                     .stream()
                     .map(
                             (entry) -> {
                                 return new AbstractMap.SimpleEntry<String, String>(
                                         entry.getKey(),
                                         entry.getValue()
                                              .stream()
                                              .map(ValidationError::fieldName)
                                              .collect(Collectors.joining(", "))
                                 );

                             }
                     )
                     .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

}
