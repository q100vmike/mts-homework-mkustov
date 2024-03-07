package mtshomework;

public class Dog extends Pet {
    public Dog() {
        name = "Dog";
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
