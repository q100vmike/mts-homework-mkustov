package mtshomework;

import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    public void createAnimal(int n) {
        System.out.println("====createAnimal(int n)====");
        Random  r = new Random();
        Animal[] animal = new Animal[n];

        for (int i = 0; i < n; i++) {
            int animalType = r.nextInt(3);//(int)Math.round(Math.random() * 4);
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
            System.out.println(animal[i]);
        }
    }
    @Override
    public void createAnimal() {
        System.out.println("====createAnimal Override====");
        Animal[] animal = new Animal[10];
        Random  r = new Random();
        int i = 0;
        do {
            int animalType = r.nextInt(3);//(int)Math.round(Math.random() * 4);
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
            System.out.println(animal[i]);
            i++;
        } while (i < animal.length);
    }
}