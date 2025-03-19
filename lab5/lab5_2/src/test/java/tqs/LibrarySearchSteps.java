package tqs;

import com.example.Book;
import com.example.Library;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarySearchSteps {
    private Library library = new Library();
    private List<Book> searchResults;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @ParameterType(".*")
    public LocalDate iso8601Date(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return LocalDate.parse(date, formatter);
    }


    @Given("a book with the title {string}, written by {string}, published on {iso8601Date}")
    public void addBookToLibrary(String title, String author, LocalDate publicationDate) {
        library.addBook(new Book(author, title, publicationDate.atStartOfDay()));
    }

    @Given("another book with the title {string}, written by {string}, published on {iso8601Date}")
    public void addAnotherBookToLibrary(String title, String author, LocalDate publicationDate) {
        library.addBook(new Book(author, title, publicationDate.atStartOfDay()));
    }



    @When("the customer searches for books written by {string}")
    public void searchBooksByAuthor(String author) {
        searchResults = library.findBooksByAuthor(author);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void searchBooksByDateRange(LocalDate from, LocalDate to) {
        searchResults = library.findBooks(from.atStartOfDay(), to.atStartOfDay());
    }

    @When("the customer searches for a book with title {string}")
    public void searchBookByTitle(String title) {
        searchResults = library.getAllBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Then("{int} books should have been found")
    public void verifyNumberOfBooksFound(int expectedCount) {
        Assertions.assertEquals(expectedCount, searchResults.size(), "Incorrect number of books found.");
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookTitle(int bookIndex, String expectedTitle) {
        Assertions.assertTrue(bookIndex > 0 && bookIndex <= searchResults.size(), "Invalid book index");
        Assertions.assertEquals(expectedTitle, searchResults.get(bookIndex - 1).getTitle(), "Book title does not match.");
    }
}
