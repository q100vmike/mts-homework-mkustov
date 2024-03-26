package mtshomework;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InvalidAnimalBirtDateException, InvalidAnimalException {
        System.out.println(LocalDate.now());
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimal();

        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        createAnimalServiceImpl.createAnimal(5);

        CreateDefaultAnimalServiceImpl createAnimalServiceI = new CreateDefaultAnimalServiceImpl();
        createAnimalServiceI.createAnimal();
    }
}
