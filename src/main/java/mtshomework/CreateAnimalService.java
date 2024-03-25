package mtshomework;

import java.util.*;

public interface CreateAnimalService {
    default Map<String, List<Animal>> createAnimal(){
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> list = new ArrayList<>();
        List<Animal> animal = new ArrayList<>(10);
        int i = 0;

        while (i < animal.size()) {
            int animalType = (int)Math.round(Math.random() * 4);
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
        }
        return animalsMap;
    }
}
