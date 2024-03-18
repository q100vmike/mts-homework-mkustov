package mtshomework;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected Double cost;
    protected String character;
    protected LocalDate birthDate;

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() throws InvalidAnimalBirtDateException {
        if (birthDate == null) {
            String [] message = {getBreed(), getName()};
            throw new InvalidAnimalBirtDateException(message);
        }
        return birthDate;
    }
}

