package mtshomework;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("Тест findDuplicateStreem: Ищем дубликаты используя streem")
    void whenAnimalsGroupByStreem() throws InvalidAnimalBirtDateException {
        Map<String, List<Animal>> result = new HashMap<>();
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
        AbstractAnimal animal6 = new Dog("Sharik");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        AbstractAnimal animal7 = new Dog("Bobik");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        List<AbstractAnimal> list = new ArrayList<>() {
            {
                add(animal1);
                add(animal2);
                add(animal3);
                add(animal4);
                add(animal5);
                add(animal6);
                add(animal7);
            }
        };
        List<Animal> listCat = new ArrayList<>() {
            {
                add(animal1);
                add(animal2);
            }
        };
        List<Animal> listShark = new ArrayList<>() {
            {
                add(animal3);
            }
        };
        List<Animal> listWolf = new ArrayList<>() {
            {
                add(animal4);
            }
        };
        List<Animal> listDog = new ArrayList<>() {
            {
                add(animal5);
                add(animal6);
                add(animal7);
            }
        };
        result.put("Cat", listCat);
        result.put("Shark", listShark);
        result.put("Wolf", listWolf);
        result.put("Dog", listDog);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Map<String, List<Animal>> expect = animalRepository.findDuplicateStreem(list);
        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findAverageAge. Средний возраст животных")
    public void whenAnimalsAgeIs17() {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Map<Animal, Integer> expect = new HashMap<>();
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
        animalRepository.findAverageAge(list);

        String expected = "Средний возраст животных: 17.0 лет\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    @DisplayName("Тест findMinConstAnimals: 3 животных с мин стоимостью отсортированные по алфавиту в обратном порядке")
    void when3AnimalsMinCostSort() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Жорик");
        animal1.birthDate = LocalDate.parse("2024-03-12");
        animal1.cost = 100D;
        AbstractAnimal animal2 = new Cat("Тузик");
        animal2.birthDate = LocalDate.parse("2020-03-12");
        animal2.cost = 1500D;
        AbstractAnimal animal3 = new Dog("Борька");
        animal3.birthDate = LocalDate.parse("1991-03-12");
        animal3.cost = 1000D;
        AbstractAnimal animal4 = new Dog("Снежок");
        animal4.birthDate = LocalDate.parse("1996-03-12");
        animal4.cost = 500D;
        AbstractAnimal animal5 = new Cat("Алик");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        animal5.cost = 100D;
        AbstractAnimal animal6 = new Shark("Юла");
        animal6.birthDate = LocalDate.parse("2002-03-12");
        animal6.cost = 400D;

        List<AbstractAnimal> list = List.of(animal1, animal2, animal3, animal4, animal5, animal6);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        List<String> expect = animalRepository.findMinConstAnimals(list);

        List<String> result = List.of("Юла", "Жорик", "Алик");

        assertTrue(expect.equals(result));
    }

    @Test
    @DisplayName("Тест findOldAndExpensive: Животные возраст которых больше 5 лет" +
            "стоимость которых больше средней стоимости всех животных. Результатом работы" +
            "метода должен быть отсортированный по дате рождения")
    void whenOlder5AndExpensive() throws InvalidAnimalBirtDateException {
        AbstractAnimal animal1 = new Cat("Жорик");
        animal1.birthDate = LocalDate.parse("2024-03-12");
        animal1.cost = 100D;
        AbstractAnimal animal2 = new Cat("Тузик");
        animal2.birthDate = LocalDate.parse("1996-03-12");
        animal2.cost = 1500D;
        AbstractAnimal animal3 = new Dog("Борька");
        animal3.birthDate = LocalDate.parse("1990-03-12");
        animal3.cost = 1000D;
        AbstractAnimal animal4 = new Dog("Снежок");
        animal4.birthDate = LocalDate.parse("1996-03-12");
        animal4.cost = 500D;
        AbstractAnimal animal5 = new Cat("Алик");
        animal5.birthDate = LocalDate.parse("2002-03-12");
        animal5.cost = 100D;
        AbstractAnimal animal6 = new Shark("Юла");
        animal6.birthDate = LocalDate.parse("2002-03-12");
        animal6.cost = 400D;

        List<AbstractAnimal> list = List.of(animal1, animal2, animal3, animal4, animal5, animal6);

        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        List<Animal> expect = animalRepository.findOldAndExpensive(list);

        List<Animal> result = List.of(animal3,animal2);

        assertTrue(expect.equals(result));
    }
}