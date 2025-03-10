import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.time.Duration;

public class Tqs43Test {
  
  private WebDriver driver;
  private WebDriverWait wait;
  private JavascriptExecutor js;

  private static final By SEARCH_BAR = By.cssSelector(".Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI");
  private static final By SEARCH_BUTTON = By.cssSelector(".Navbar_searchBarContainer__3UbnF .Navbar_searchBtnIcon__25k0u");
  private static final By SEARCH_RESULTS = By.cssSelector("[data-testid=book-search-item]");

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
    
    driver = new FirefoxDriver();
    driver.manage().window().setSize(new Dimension(1850, 1053));
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    js = (JavascriptExecutor) driver;
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void tqs43() {
    driver.get("https://cover-bookstore.onrender.com/");

    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

    System.out.println("Page Source: " + driver.getPageSource());

    Boolean isPresent = (Boolean) js.executeScript("return document.querySelector('.Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI') !== null;");
    System.out.println("Search bar present: " + isPresent);
    assertTrue(isPresent, "Search bar is not found in DOM");

    WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BAR));
    searchBar.click();
    searchBar.sendKeys("Harry Potter");

    WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
    searchButton.click();

    wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS));

    List<WebElement> results = driver.findElements(SEARCH_RESULTS);
    assertFalse(results.isEmpty(), "No search results found!");

    System.out.println("First search result: " + results.get(0).getText());

    boolean bookFound = results.stream()
                               .anyMatch(element -> element.getText().contains("Harry Potter and the Sorcerer's Stone"));
    assertTrue(bookFound, "Expected book title not found in search results!");
  }
}
