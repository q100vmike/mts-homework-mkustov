package mtshomework;

import java.time.LocalDate;

public class SearchServiceImpl implements SearchService {
    @Override
    public void checkLeapYearAnimal(AbstractAnimal animal) throws InvalidAnimalException, InvalidAnimalBirtDateException {
        if (animal == null) {
            LocalDate today = LocalDate.now();
            throw new InvalidAnimalException("На вход пришел некорректное животное " + today);
        }
        int year = animal.getBirthDate().getYear();
        boolean leapyear = (
                year > 1584 &&
                        (
                                (year % 400 == 0) ||
                                        (year %4 == 0 && year % 100 != 0)
                        )
        );
        if (leapyear)
            System.out.println(animal.getName() + " был рожден в високосный год "
                    + Integer.toString(year));
        else
            System.out.println(animal.getName() + " был рожден в НЕ високосный год "
                    + Integer.toString(year));
    }
}
