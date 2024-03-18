package mtshomework;

public class Shark extends Predator {
    public Shark() {
        name = "Shark";
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }

}

