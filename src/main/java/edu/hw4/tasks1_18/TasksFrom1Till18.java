package edu.hw4.tasks1_18;

import edu.hw4.given.Animal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TasksFrom1Till18 {

    private static final Integer GIVEN_MIN_HEIGHT_FOR_TASK11 = 100;

    public List<Animal> task1SortAnimalFromShortToTall(List<Animal> animals) {
        return animals.stream()
                      .sorted(Comparator.comparing(Animal::height))
                      .toList();
    }

    public List<Animal> task2SortAnimalFromHeavyToLight(List<Animal> animals) {
        return animals.stream()
                      .sorted(Comparator.comparing(Animal::weight)
                                        .reversed())
                      .toList();
    }

    public Map<Animal.Type, Long> task3AnimalToType(List<Animal> animals) {
        return animals.stream()
                      .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public Animal task4TheLongestName(List<Animal> animals) {
        return animals.stream()
                      .max(Comparator.comparingInt(o -> o.name()
                                                         .length()))
                      .get();
    }

    public Animal.Sex task5TheMostPopularGender(List<Animal> animals) {
        return animals.stream()
                      .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
                      .entrySet()
                      .stream()
                      .max(Map.Entry.comparingByValue())
                      .map(Map.Entry::getKey)
                      .get();
    }

    public Map<Animal.Type, Animal> task6TheMostHeavyAnimalByType(List<Animal> animals) {
        return animals.stream()
                      .collect(Collectors.groupingBy(Animal::type,
                              Collectors.maxBy(Comparator.comparingInt(Animal::weight))))
                      .entrySet()
                      .stream()
                      .filter(typeOptionalEntry -> typeOptionalEntry.getValue()
                                                                    .isPresent())
                      .collect(Collectors.toMap(Map.Entry::getKey, typeOptionalEntry -> typeOptionalEntry.getValue()
                                                                                                         .get()));
    }

    public Animal task7TheOldestAnimal(List<Animal> animals) {
        return animals.stream()
                      .max(Comparator.comparingInt(Animal::age))
                      .get();
    }

    public Optional<Animal> task8TheMostHeavyAnimalShorterMaxHeight(List<Animal> animals, Integer maxHeight) {
        return animals.stream()
                      .filter(animal -> animal.height() < maxHeight)
                      .max(Comparator.comparingInt(Animal::weight));
    }

    public Integer task9SumOfPaws(List<Animal> animals) {
        return animals.stream()
                      .map(Animal::paws)
                      .reduce(Integer::sum)
                      .get();
    }

    public List<Animal> task10AnimalWhereAgeAndPawsDifferent(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.age() != animal.paws())
                      .toList();
    }

    public List<Animal> task11AnimalThatBitsAndTaller100(List<Animal> animals) {
        return animals.stream()
                      .filter(Animal::bites)
                      .filter(animal -> animal.height() > GIVEN_MIN_HEIGHT_FOR_TASK11)
                      .toList();
    }

    public Integer task12AnimalWhichWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.weight() > animal.height())
                      .toList()
                      .size();
    }

    public List<Animal> task13AnimalWhichNameMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.name()
                                              .split(" ").length > 2)
                      .toList();
    }

    public boolean task14isExistDogTallerMinTall(List<Animal> animals, Integer minTall) {
        return animals.stream()
                      .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > minTall);
    }

    public Integer task15WeightOfAnimalsOlderMinAgeYoungerMaxAge(List<Animal> animals,
                                                                 Integer minaAge,
                                                                 Integer maxAge) {
        return animals.stream()
                      .filter(animal -> animal.age() >= minaAge && animal.age() <= maxAge)
                      .map(Animal::weight)
                      .reduce(Integer::sum)
                      .orElse(0);
    }

    public List<Animal> task16SortedByTypeSexName(List<Animal> animals) {
        return animals.stream()
                      .sorted(Comparator.comparing(Animal::type)
                                        .thenComparing(Animal::sex)
                                        .thenComparing(Animal::name))
                      .toList();
    }

    public boolean task17SpidersBiteMoreThanDogs(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.bites() && (animal.type() == Animal.Type.SPIDER
                              || animal.type() == Animal.Type.DOG))
                      .collect(Collectors.groupingBy(Animal::type, Collectors.counting()))
                      .entrySet()
                      .stream()
                      .reduce((firstEntry, secondEntry) -> {
                          if (firstEntry.getValue() > secondEntry.getValue()) {
                              return firstEntry;
                          }
                          if (firstEntry.getValue() < secondEntry.getValue()) {
                              return secondEntry;
                          }
                          return Map.entry(Animal.Type.FISH, 1L);

                      })
                      .stream()
                      .anyMatch(typeLongEntry -> typeLongEntry.getKey() == Animal.Type.SPIDER);
    }

    public Animal task18TheMostHeavyFish(List<List<Animal>> animals) {
        return animals.stream()
                      .flatMap(Collection::stream)
                      .filter(animal -> animal.type() == Animal.Type.FISH)
                      .max(Comparator.comparingInt(Animal::weight))
                      .get();
    }


}
