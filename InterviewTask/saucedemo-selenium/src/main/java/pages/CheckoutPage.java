package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zip = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillShippingDetails(String fname, String lname, String zipCode) {
        type(firstName, fname);
        type(lastName, lname);
        type(zip, zipCode);
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }
}
