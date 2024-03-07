package mtshomework;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected Double cost;
    protected String character;

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
}

