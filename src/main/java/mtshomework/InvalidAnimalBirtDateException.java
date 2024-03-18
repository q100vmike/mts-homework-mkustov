package mtshomework;

public class InvalidAnimalBirtDateException extends Exception {
    public InvalidAnimalBirtDateException(String[] message) {
        super(String.format("у животного %s %s не указана дата его рождения", message[0], message[1]));
    }
}
