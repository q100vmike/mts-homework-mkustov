package mtshomework;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InvalidAnimalBirtDateException, InvalidAnimalException {
/*
        Pet cat = new Cat("Kitty");
        //cat = null;
        SearchServiceImpl searchService = new SearchServiceImpl();
        try {
            searchService.checkLeapYearAnimal(cat);
        } catch (InvalidAnimalBirtDateException re) {
            throw new InvalidAnimalException("Работа метода завершилась ошибкой");
        }

 */
        System.out.println(LocalDate.now());
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimal();

        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        createAnimalServiceImpl.createAnimal(5);

        CreateDefaultAnimalServiceImpl createAnimalServiceI = new CreateDefaultAnimalServiceImpl();
        createAnimalServiceI.createAnimal();
    }
}
