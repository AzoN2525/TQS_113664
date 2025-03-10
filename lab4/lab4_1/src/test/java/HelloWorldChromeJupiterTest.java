import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

class HelloWorldChromeJupiterTest {

    static final Logger log = LoggerFactory.getLogger(HelloWorldChromeJupiterTest.class);

    private WebDriver driver; 


    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup(); 
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(); 
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        
        log.debug("The title of {} is {}", sutUrl, title); 

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java"); 
        teardown();
    }

    @AfterEach
    void teardown() {
        driver.quit(); 
    }

    @Test 
    void testSlowCalculator() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 

        driver.findElement(By.linkText("Slow calculator")).click();
        title = driver.getTitle();
        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
        teardown();
    }

}