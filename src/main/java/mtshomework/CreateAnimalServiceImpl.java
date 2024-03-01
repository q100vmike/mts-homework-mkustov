package mtshomework;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    public void createAnimal(int n) {
        System.out.println("createAnimal(int n)");
        Animal[] animal = new Animal[n];

        for (int i = 0; i < n; i++) {
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
    @Override
    public void createAnimal() {
        System.out.println("createAnimal Override");
        Animal[] animal = new Animal[10];
        int i = 0;
        do {
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
            i++;
        } while (i < animal.length);
    }
}