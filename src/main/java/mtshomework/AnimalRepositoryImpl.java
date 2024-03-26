package mtshomework;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

public class AnimalRepositoryImpl implements AnimalRepository{
    @Override
    public Map<String, LocalDate> findLeapYearNames(List<AbstractAnimal> animals) {
        SearchServiceImpl searchService = new SearchServiceImpl();

        return animals.stream().filter(a -> {
            try {
                return searchService.checkLeapYearAnimal(a);
            } catch (InvalidAnimalBirtDateException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toMap(a -> a.getBreed() + " " + a.getName(), b -> b.birthDate));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<AbstractAnimal> animals, Integer N) {
        Map<Animal, Integer> map;
        animals = animals.stream()
                .sorted(Comparator.comparing(a -> {
                    try {
                        return a.getBirthDate();
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());
        map = animals.stream()
                .filter(a -> N < Math.toIntExact(YEARS.between(a.birthDate, LocalDate.now())))
                .collect(Collectors.toMap(a -> a, b -> Math.toIntExact(YEARS.between(b.birthDate, LocalDate.now())))) ;

        if (map.isEmpty() && !animals.isEmpty()) {
            Integer firstYearsOld = Math.toIntExact(YEARS.between(animals.get(0).birthDate, LocalDate.now()));
            map.put(animals.get(0), firstYearsOld);
        }
        return map;
    }

   @Override
    public Map<String, Integer> findDuplicate(List<AbstractAnimal> animals) {
        Map<String, Integer> map = new HashMap<>();
        for (AbstractAnimal animal : animals) {
            map.merge(animal.getBreed(), 1, (oldVal, newVal) -> oldVal + 1);
        }
        return map;
    }
    @Override
    public Map<String, List<Animal>> findDuplicateStreem(List<AbstractAnimal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::getBreed, Collectors.toList()));
    }

    @Override
    public void findAverageAge(List<AbstractAnimal> animals) {
        Double old = animals.stream()
                .mapToInt(a -> Math.toIntExact(YEARS.between(a.birthDate, LocalDate.now())))
                .average()
                .orElse(0);
        System.out.println("Средний возраст животных: " + Double.toString(old)+ " лет");
    }

    @Override
    public List<Animal> findOldAndExpensive(List<AbstractAnimal> animals) {
        Double avg = animals.stream()
                .mapToDouble(a -> a.cost)
                .average()
                .orElse(0);

        List<Animal> list = animals.stream()
                .filter(a -> Math.toIntExact(YEARS.between(a.birthDate, LocalDate.now())) > 5)
                .filter(a -> a.cost > avg)
                .sorted(Comparator.comparing(a -> a.birthDate))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<String> findMinConstAnimals(List<AbstractAnimal> animals) {
        return animals.stream()
                .sorted((a, b) -> a.cost.compareTo(b.cost))
                .limit(3)
                .map(a -> a.name)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
