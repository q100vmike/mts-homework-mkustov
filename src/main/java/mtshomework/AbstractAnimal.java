package mtshomework;

import java.time.LocalDate;

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
            throw new InvalidAnimalBirtDateException("у животного "
                    + getBreed()
                    + " "
                    + getName()
                    + " не указана дата его рождения");
        }
        return birthDate;
    }
}

