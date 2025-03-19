package tqs;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;



public class LibrarySearchSteps {
    private WebDriver driver;

    @Given("the user is on the library homepage")
    public void theUserIsOnTheLibraryHomepage() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); // Update with your path
        driver = new ChromeDriver();
        driver.get("https://cover-bookstore.onrender.com/");
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String bookTitle) {
        WebElement searchBox = driver.findElement(By.id("book-search-input")); // Update with correct ID
        searchBox.sendKeys(bookTitle);
        WebElement searchButton = driver.findElement(By.id("Navbar_searchBtn")); // Update with correct ID
        searchButton.click();
    }

    @When("the user searches for books written by {string}")
    public void theUserSearchesForBooksByAuthor(String author) {
        WebElement authorSearchBox = driver.findElement(By.id("book-search-input")); // Update with correct ID
        authorSearchBox.sendKeys(author);
        WebElement searchButton = driver.findElement(By.id("Navbar_searchBtn")); // Update with correct ID
        searchButton.click();
    }

    @Then("the search results should contain {string}")
    public void theSearchResultsShouldContain(String expectedTitle) {
        List<WebElement> results = driver.findElements(By.className("SearchList_bookTitle")); // Update with correct class
        boolean found = results.stream().anyMatch(e -> e.getText().equals(expectedTitle));
        Assertions.assertTrue(found, "Expected book not found in search results");
        driver.quit();
    }

    @Then("the search results should be empty")
    public void theSearchResultsShouldBeEmpty() {
        List<WebElement> results = driver.findElements(By.className("SearchList_bookTitle")); // Update with correct class
        Assertions.assertTrue(results.isEmpty(), "Search results were not empty");
        driver.quit();
    }
}
