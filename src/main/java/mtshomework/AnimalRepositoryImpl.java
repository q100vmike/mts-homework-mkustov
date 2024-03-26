package mtshomework;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

public class AnimalRepositoryImpl implements AnimalRepository{
    @Override
    public Map<String, LocalDate> findLeapYearNames(List<AbstractAnimal> animals) throws InvalidAnimalBirtDateException {
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
        Integer yearsOld = 0;
        Map<Animal, Integer> map = new HashMap<>();
/*        Collections.sort(animals, new Comparator<AbstractAnimal>(){
            public int compare(AbstractAnimal o1, AbstractAnimal o2)
            {
                try {
                    return o1.getBirthDate().compareTo(o2.getBirthDate());
                } catch (InvalidAnimalBirtDateException e) {
                    throw new RuntimeException(e);
                }
            }
        });*/
        map = animals.stream().sorted(Comparator.comparing(a -> a.birthDate))
                .filter(a -> N < Math.toIntExact(YEARS.between(a.birthDate, LocalDate.now())))
                .collect(Collectors.toMap(a -> a, b -> Math.toIntExact(YEARS.between(b.birthDate, LocalDate.now()))));
/*
        for (int i = 0; i < animals.size(); i++)  {
            yearsOld = Math.toIntExact(YEARS.between(animals.get(i).birthDate, LocalDate.now()));
            if (N < yearsOld) {
                map.put(animals.get(i), yearsOld);
            }
        }
        */
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
}
