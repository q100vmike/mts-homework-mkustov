package mtshomework;

import java.util.*;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    public Map<String, List<Animal>> createAnimal(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        Random  r = new Random();
        List<Animal> animal = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int animalType = r.nextInt(3);
            switch (animalType) {
                case 0:
                    animal.add(new Cat());
                    break;
                case 1:
                    animal.add(new Dog());
                    break;
                case 2:
                    animal.add(new Shark());
                    break;
                default:
                    animal.add(new Wolf());
            }
            String animalName = animal.get(i).getName();
            if (!animalsMap.containsKey(animalName)) {
                animalsMap.put(animalName, new ArrayList<>());
            }
            animalsMap.get(animalName).add(animal.get(i));
            System.out.println(animal.get(i));
        }
        return animalsMap;
    }
    @Override
    public Map<String, List<Animal>> createAnimal() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animal = new ArrayList<>(10);
        Random  r = new Random();
        int i = 0;
        do {
            int animalType = r.nextInt(3);
            switch (animalType) {
                case 0:
                    animal.add(new Cat());
                    break;
                case 1:
                    animal.add(new Dog());
                    break;
                case 2:
                    animal.add(new Shark());
                    break;
                default:
                    animal.add(new Wolf());
            }
            String animalName = animal.get(i).getName();
            if (!animalsMap.containsKey(animalName)) {
                animalsMap.put(animalName, new ArrayList<>());
            }
            animalsMap.get(animalName).add(animal.get(i));
            System.out.println(animal.get(i));
            i++;
        } while (i < animal.size());

        return animalsMap;
    }
}