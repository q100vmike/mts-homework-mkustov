package mtshomework;

public class Wolf extends Predator {
    public Wolf() {
        name = "Wolf";
    }
    public Wolf(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }

}