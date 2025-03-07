class HelloWorldChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private WebDriver driver; 1

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup(); 2
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(); 3
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); 4
        String title = driver.getTitle(); 5
        log.debug("The title of {} is {}", sutUrl, title); 6

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java"); 7
    }

    @AfterEach
    void teardown() {
        driver.quit(); 8
    }

}