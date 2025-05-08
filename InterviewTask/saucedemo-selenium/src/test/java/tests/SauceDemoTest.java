package tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.log4j.Logger;

public class SauceDemoTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    ExtentReports extent;
    ExtentTest test;
    Logger log = Logger.getLogger(SauceDemoTest.class);

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "local/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Extent Report setup
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("Sauce Demo Purchase Test");

        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test
    public void purchaseProductTest() {
        try {
            log.info("Logging in...");
            loginPage.login("standard_user", "secret_sauce");
            test.pass("Login successful");

            log.info("Checking product...");
            assert productPage.isProductVisible();
            test.pass("Product visible");

            productPage.addToCart();
            assert productPage.getCartCount().equals("1");
            test.pass("Product added to cart");

            productPage.goToCart();
            cartPage.clickCheckout();

            checkoutPage.fillShippingDetails("John", "Doe", "12345");
            checkoutPage.clickFinish();

            String confirmation = confirmationPage.getConfirmationMessage();
            assert confirmation.contains("Thank you for your order");
            test.pass("Order completed");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            log.error("Exception occurred", e);
        }
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
