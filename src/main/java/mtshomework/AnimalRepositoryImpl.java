package mtshomework;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

public class AnimalRepositoryImpl implements AnimalRepository{
    @Override
    public Map<String, LocalDate> findLeapYearNames(List<AbstractAnimal> animals) throws InvalidAnimalBirtDateException {
        Map<String, LocalDate> map = new HashMap<>();
        SearchServiceImpl searchService = new SearchServiceImpl();

        for (AbstractAnimal animal : animals){
            if (searchService.checkLeapYearAnimal(animal)) {
                map.put(animal.getBreed() + " " + animal.getName(), animal.birthDate);
            }
        }
        return map;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<AbstractAnimal> animals, Integer N) {
        Integer yearsOld = 0;
        Map<Animal, Integer> map = new HashMap<>();
        Collections.sort(animals, new Comparator<AbstractAnimal>(){
            public int compare(AbstractAnimal o1, AbstractAnimal o2)
            {
                try {
                    return o1.getBirthDate().compareTo(o2.getBirthDate());
                } catch (InvalidAnimalBirtDateException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        for (int i = 0; i < animals.size(); i++)  {
            yearsOld = Math.toIntExact(YEARS.between(animals.get(i).birthDate, LocalDate.now()));
            if (N < yearsOld) {
                map.put(animals.get(i), yearsOld);
            }
        }
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
                .sorted()
                .collect(Collectors.toList());
    }


}
