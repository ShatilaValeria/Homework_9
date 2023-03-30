package сomputersAndNetworks;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class ComputersAndNetworks {
    private static ChromeOptions options;
    private static WebDriver driver;
    private static final String PRODUCT_NAME_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and text()='%s']";
    private static final String PRODUCT_NAME_ELEMENT = "//*[contains(text(), '%s')]";
    public static logger.Logger log = logger.Logger.getInstance();


    @BeforeMethod
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
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, "Компьютеры и\u00A0сети"))).click();
        log.info("Click on the 'Компьютеры и сети'");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckComputersAndNetworks() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_ELEMENT, "Ноутбуки, компьютеры, мониторы"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the 'Ноутбуки, компьютеры, мониторы'");
    }

    @Test
    public void testCheckAccessories() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_ELEMENT, "Комплектующие"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the 'Комплектующие'");
    }

    @Test
    public void testCheckDataStorage() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_ELEMENT, "Хранение данных"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the 'Хранение данных'");
    }

    @Test
    public void testCheckNetworkEquipment() {
        boolean expected = driver.findElement(By.xpath(String.format(PRODUCT_NAME_ELEMENT, "Сетевое оборудование"))).isDisplayed();
        Assert.assertTrue(expected);
        log.info("Checking the 'Сетевое оборудование'");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        log.info("Closing the browser");
    }
}
