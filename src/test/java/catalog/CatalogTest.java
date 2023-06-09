package catalog;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class CatalogTest {
    private static ChromeOptions options;
    private static WebDriver driver;
    private static final String PRODUCT_NAME_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and text()='%s']";
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckElectronics() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Электроника"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the Еlectronics section");
    }

    @Test
    public void testCheckComputersAndNetworks() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Компьютеры и\u00A0сети"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the section Computers and networks");
    }

    @Test
    public void testCheckAppliances() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Бытовая техника"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the electronics section");
    }

    @Test
    public void testCheckConstructionAndRepair() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Стройка и\u00A0ремонт"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the section Construction and repair");
    }

    @Test
    public void testCheckHouseAndGarden() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Дом и\u00A0сад"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the Home and Garden section");
    }

    @Test
    public void testCheckAutoAndMoto() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Авто и\u00A0мото"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the Auto and Moto section");
    }

    @Test
    public void testCheckBeautyAndSports() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Красота и\u00A0спорт"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the section Beauty and sports");
    }

    @Test
    public void testCheckForChildrenAndMothers() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Детям и\u00A0мамам"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the section for Children and mothers");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        log.info("Closing the browser");
    }
}
