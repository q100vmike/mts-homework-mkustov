package mtshomework;

public interface CreateAnimalService {
    default void createAnimal(){
        System.out.println("interface CreateAnimalService");
        Animal[] animal = new Animal[10];
        int i = 0;
        while (i < animal.length) {
            int animalType = (int)Math.round(Math.random() * 4);
            switch (animalType) {
                case 0:
                    animal[i] = new Cat();
                    break;
                case 1:
                    animal[i] = new Dog();
                    break;
                case 2:
                    animal[i] = new Shark();
                    break;
                default:
                    animal[i] = new Wolf();
            }
        }
    }
}
