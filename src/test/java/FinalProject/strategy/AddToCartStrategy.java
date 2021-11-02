package FinalProject.strategy;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface AddToCartStrategy {

    List<WebElement> addProductToCart(int amount);
}
