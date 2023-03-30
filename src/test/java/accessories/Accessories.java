package accessories;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;


public class Accessories {
    private static ChromeOptions options;
    private static WebDriver driver;
    private static final String PRODUCT_NAME_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and text()='%s']";
    private static final String PRODUCT_NAME_CATEGORY = "//*[contains(text(), '%s')]";
    private static final By PRODUCT_NAME = By.xpath("//div[text()=' Комплектующие ']/..//span[contains(@class, 'title')]");
    private static final By PRODUCT_COUNT = By.xpath("//div[text()=' Комплектующие ']/..//span[3]/text()[1]/..");
    private static final By PRODUCT_PRICE = By.xpath("//div[text()=' Комплектующие ']/..//span//following-sibling::text()[1]/..");
    public static logger.Logger log = logger.Logger.getInstance();

    @BeforeTest
    public void init() {
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://onliner.by");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        log.info("Open onliner.by");
        driver.findElement(By.cssSelector(".b-main-navigation__link")).click();
        log.info("Click on the catalog");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Компьютеры и\u00A0сети"))).click();
        log.info("Click on the 'Компьютеры и сети'");
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_CATEGORY, "Комплектующие"))).click();
        log.info("Click on the 'Комплектующие'");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckName() {
        boolean expected = driver.findElements(PRODUCT_NAME).stream().allMatch(WebElement::isDisplayed);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(expected);
        log.info("Checking the product name");
    }

    @Test
    public void testCheckCount() {
        boolean expected = driver.findElements(PRODUCT_COUNT).stream().allMatch(WebElement::isDisplayed);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(expected);
        log.info("Checking the product count");
    }

    @Test
    public void testCheckMinPrice() {
        boolean expected = driver.findElements(PRODUCT_PRICE).stream().allMatch(WebElement::isDisplayed);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(expected);
        log.info("Checking the product price");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        log.info("Closing the browser");
    }
}
