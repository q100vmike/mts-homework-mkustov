package mtshomework;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalRepositoryImplTest {

    @Test
    @DisplayName("Тест findLeapYearNames: 3 из 4 животных родились в високосный год")
    void when3AnimalsLeapYear() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("2024-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2020-03-12");
        AbstractAnimal animal3 = new Shark("Akula");
        animal3.birthDate = LocalDate.parse("1991-03-12");
        AbstractAnimal animal4 = new Wolf("Volk");
        animal4.birthDate = LocalDate.parse("1996-03-12");
        List<AbstractAnimal> list = List.of(animal1, animal2, animal3, animal4);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<String, LocalDate> expect = animalRepository.findLeapYearNames(list);

        Map<String, LocalDate> result = Map.of("Cat Kitty", LocalDate.parse("2024-03-12")
                , "Cat Tom", LocalDate.parse("2020-03-12")
                , "Wolf Volk", LocalDate.parse("1996-03-12"));

        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findLeapYearNames: Нет животных родившихся в високосный год")
    void whenNoAnimalsLeapYear() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("2095-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2023-03-12");
        AbstractAnimal animal3 = new Shark("Akula");
        animal3.birthDate = LocalDate.parse("2015-03-12");
        AbstractAnimal animal4 = new Wolf("Volk");
        animal4.birthDate = LocalDate.parse("2022-03-12");
        List<AbstractAnimal> list = List.of(animal1, animal2, animal3, animal4);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<String, LocalDate> expect = animalRepository.findLeapYearNames(list);

        assertTrue(expect.isEmpty());
    }

    @Test
    @DisplayName("Тест findOlderAnimal: 4 животных старше 5и лет")
    void when4AnimalsOlder5Years() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("1995-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2023-03-12");
        AbstractAnimal animal3 = new Shark("Akula");
        animal3.birthDate = LocalDate.parse("2015-03-12");
        AbstractAnimal animal4 = new Wolf("Volk");
        animal4.birthDate = LocalDate.parse("2000-03-12");
        AbstractAnimal animal5 = new Dog("Polkan");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        List<AbstractAnimal> list = new ArrayList<>() {
            {
                add(animal1);
                add(animal2);
                add(animal3);
                add(animal4);
                add(animal5);
            }
        };
        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<Animal, Integer> expect = animalRepository.findOlderAnimal(list, 5);
        Map<Animal, Integer> result = Map.of(animal1, 29
                , animal4, 24
                , animal5, 22
                , animal3, 9);

        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findOlderAnimal: нет животных старше 100 лет, возвращаем Kitty 29лет")
    void whenNoAnimalsOlder100Years() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("2015-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2023-03-12");
        AbstractAnimal animal3 = new Shark("Akula");
        animal3.birthDate = LocalDate.parse("1995-03-12");
        AbstractAnimal animal4 = new Wolf("Volk");
        animal4.birthDate = LocalDate.parse("2000-03-12");
        AbstractAnimal animal5 = new Dog("Polkan");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        List<AbstractAnimal> list = new ArrayList<>() {
            {
                add(animal1);
                add(animal2);
                add(animal3);
                add(animal4);
                add(animal5);
            }
        };
        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<Animal, Integer> expect = animalRepository.findOlderAnimal(list, 100);
        Map<Animal, Integer> result = Map.of(animal3, 29);

        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findDuplicate: 3 кота 2 собаки 1 акула")
    void when3Cats2Dogs1SharkAnimals() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("2024-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2020-03-12");
        AbstractAnimal animal3 = new Dog("Akula");
        animal3.birthDate = LocalDate.parse("1991-03-12");
        AbstractAnimal animal4 = new Dog("Volk");
        animal4.birthDate = LocalDate.parse("1996-03-12");
        AbstractAnimal animal5 = new Cat("Polkan");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        AbstractAnimal animal6 = new Shark("Polkan");
        animal6.birthDate = LocalDate.parse("2002-03-12");

        List<AbstractAnimal> list = List.of(animal1, animal2, animal3, animal4, animal5, animal6);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<String, Integer> expect = animalRepository.findDuplicate(list);

        Map<String, Integer> result = Map.of("Cat", 3
                , "Dog", 2
                , "Shark", 1);

        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findOlderAnimal: если у животного Shark Akula нет даты рождения")
    void whenAnimalsHaveNullBirthday() throws InvalidAnimalBirtDateException {
        Map<Animal, Integer> expect = new HashMap<>();
        AbstractAnimal animal1 = new Cat("Kitty");
        animal1.birthDate = LocalDate.parse("2015-03-12");
        AbstractAnimal animal2 = new Cat("Tom");
        animal2.birthDate = LocalDate.parse("2023-03-12");
        AbstractAnimal animal3 = new Shark("Akula");
        // animal3.birthDate = LocalDate.parse("1995-03-12");
        AbstractAnimal animal4 = new Wolf("Volk");
        animal4.birthDate = LocalDate.parse("2000-03-12");
        AbstractAnimal animal5 = new Dog("Polkan");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        List<AbstractAnimal> list = new ArrayList<>() {
            {
                add(animal1);
                add(animal2);
                add(animal3);
                add(animal4);
                add(animal5);
            }
        };
        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Exception exception = assertThrows(RuntimeException.class, () -> animalRepository.findOlderAnimal(list, 100));
        String expectedMessage = "у животного Shark Akula не указана дата его рождения";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}