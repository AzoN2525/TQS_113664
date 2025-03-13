import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class SearchTest {
  
    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        searchPage = new SearchPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchForHarryPotter() {
        searchPage.visit("https://cover-bookstore.onrender.com/");
        searchPage.searchForBook("Harry Potter");
        assertTrue(searchPage.isBookFound("Harry Potter and the Sorcerer's Stone"), 
                   "Book not found in search results!");
    }
}
