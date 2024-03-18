package mtshomework;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimal();

        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        createAnimalServiceImpl.createAnimal(5);

        CreateAnimalService createAnimalServiceI = new CreateAnimalService(){};
        createAnimalServiceI.createAnimal();
    }
}
