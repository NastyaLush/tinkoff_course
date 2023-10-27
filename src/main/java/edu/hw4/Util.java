package edu.hw4;

import edu.hw4.given.Animal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Util {

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
                      .collect(Collectors
                              .groupingBy(Animal::type, Collectors
                                      .maxBy(Comparator
                                              .comparingInt(Animal::weight))))
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

    public Optional<Animal> task8TheMostHeavyAnimalShorterK(List<Animal> animals, Integer k) {
        return animals.stream()
                      .filter(animal -> animal.height() < k)
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

    public List<Animal> task12AnimalWhichWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.weight() > animal.height())
                      .toList();
    }

    public List<Animal> task13AnimalWhichNameMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
                      .filter(animal -> animal.name()
                                              .split(" ").length > 2)
                      .toList();
    }

    public boolean task14isExistDogTallerK(List<Animal> animals, Integer k) {
        return animals.stream()
                      .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public Integer task15WeightOfAnimalsOlderKYoungerL(List<Animal> animals, Integer k, Integer l) {
        return animals.stream()
                      .filter(animal -> animal.age() > k && animal.age() < l)
                      .map(Animal::weight)
                      .reduce(Integer::sum)
                      .get();
    }

    public List<Animal> task16WeightOfAnimalsOlderKYoungerL(List<Animal> animals, Integer k, Integer l) {
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
                      .sorted(Map.Entry.comparingByValue())
                      .limit(1)
                      .allMatch(typeLongEntry -> typeLongEntry.getKey()
                                                              .equals(Animal.Type.SPIDER));
    }

    public Animal task18TheMostHeavyFish(List<List<Animal>> animals) {
        return animals.stream()
                      .flatMap(Collection::stream)
                      .filter(animal -> animal.type() == Animal.Type.FISH)
                      .max(Comparator.comparingInt(Animal::weight))
                      .orElse(null);
    }


}
