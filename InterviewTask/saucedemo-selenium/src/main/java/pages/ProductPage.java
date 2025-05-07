package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private By product = By.xpath("//div[text()='Sauce Labs Backpack']");
    private By addToCart = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductVisible() {
        return driver.findElement(product).isDisplayed();
    }

    public void addToCart() {
        click(addToCart);
    }

    public String getCartCount() {
        return getText(cartBadge);
    }

    public void goToCart() {
        click(cartIcon);
    }
}
