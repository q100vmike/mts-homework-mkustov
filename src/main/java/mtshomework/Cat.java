package mtshomework;

public class Cat extends Pet {
    public Cat() {
        name = "Cat";
    }
    public Cat(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
