import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    private static final By SEARCH_BAR = By.cssSelector("[data-testid=book-search-input]");
    private static final By SEARCH_BUTTON = By.cssSelector("[data-testid=search-button]");
    private static final By SEARCH_RESULTS = By.cssSelector("[data-testid=book-search-item]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchForBook(String bookTitle) {
        // Aguardar o carregamento do elemento antes da verificação
        wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_BAR));

        Boolean isPresent = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.querySelector('[data-testid=search-bar]') !== null;");
        System.out.println("Search bar present: " + isPresent);

        if (!isPresent) {
            throw new RuntimeException("Search bar not found!");
        }

        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BAR));
        searchBar.click();
        searchBar.sendKeys(bookTitle);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
        searchButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS));
    }

    public boolean isBookFound(String bookTitle) {
        List<WebElement> results = driver.findElements(SEARCH_RESULTS);
        return results.stream().anyMatch(element -> element.getText().contains(bookTitle));
    }
}
