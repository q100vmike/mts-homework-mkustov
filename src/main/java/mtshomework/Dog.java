package mtshomework;

public class Dog extends Pet {
    public Dog() {
        name = "Dog";
    }
    public Dog(String name) {
        this.name = name;
        this.breed = "Dog";
    }
    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }

}
