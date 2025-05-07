package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {
    private By message = By.className("complete-header");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return getText(message);
    }
}
