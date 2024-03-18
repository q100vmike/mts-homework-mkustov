package mtshomework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceImplTest {

    @Test
    @DisplayName("Создание Cat високосный год")
    public void whenCreateCatLeapYearTrue() throws InvalidAnimalException, InvalidAnimalBirtDateException {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Pet cat = new Cat("Kitty");
        cat.birthDate = LocalDate.parse("2024-03-12");
        SearchServiceImpl searchService = new SearchServiceImpl();
        searchService.checkLeapYearAnimal(cat);
        String expected = "Kitty был рожден в високосный год 2024\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    @DisplayName("Создание Shark НЕ високосный год")
    public void whenCreateSharkLeapYearFalse() throws InvalidAnimalException, InvalidAnimalBirtDateException {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Predator shark = new Shark("Killer");
        shark.birthDate = LocalDate.parse("2001-03-12");
        SearchServiceImpl searchService = new SearchServiceImpl();
        searchService.checkLeapYearAnimal(shark);
        String expected = "Killer был рожден в НЕ високосный год 2001\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    @DisplayName("Ошибка создания животного т.к. null")
    public void whenCreateSharkNullException() throws InvalidAnimalException, InvalidAnimalBirtDateException {
        Predator shark = null;
        SearchServiceImpl searchService = new SearchServiceImpl();
        Exception exception = assertThrows(InvalidAnimalException.class, () -> searchService.checkLeapYearAnimal(shark));
        String expectedMessage = "На вход пришел некорректное животное " + LocalDate.now();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    @DisplayName("Ошибка создания животного т.к. не указата дата его рождения (BirthDate)")
    public void whenCreateSharkBirtDateException() throws InvalidAnimalException, InvalidAnimalBirtDateException {
        Predator wolf = new Wolf("Volk");
        wolf.breed = "gray";
        SearchServiceImpl searchService = new SearchServiceImpl();
        Exception exception = assertThrows(InvalidAnimalBirtDateException.class, () -> searchService.checkLeapYearAnimal(wolf));
        String expectedMessage = "у животного gray Volk не указана дата его рождения";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }
    @ParameterizedTest
    @DisplayName("Параметризованный тест создания животных")
    @ValueSource(strings = {"2024-03-12", "1992-01-01", "2004-09-10"})
    void whenCreateCatsLeapYearTrue(String year) throws InvalidAnimalException, InvalidAnimalBirtDateException {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Pet cat = new Cat("Kitty");
        cat.birthDate = LocalDate.parse(year);
        SearchServiceImpl searchService = new SearchServiceImpl();
        searchService.checkLeapYearAnimal(cat);
        String expected = String.format("Kitty был рожден в високосный год %s\r\n", year.substring(0, 4));
        assertEquals(expected, out.toString());

    }
}