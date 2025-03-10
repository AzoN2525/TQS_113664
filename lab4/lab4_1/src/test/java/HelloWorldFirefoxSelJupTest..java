import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SeleniumJupiter.class)
class HelloWorldFirefoxSelJupTest {

    @Test
    void test() {
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver"); // Substitua pelo caminho correto
        FirefoxOptions options = new FirefoxOptions();
        FirefoxDriver driver = new FirefoxDriver(options);

        // Teste
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();

        // Verificação
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");

        // Fechar o navegador
        driver.quit();
    }
}
