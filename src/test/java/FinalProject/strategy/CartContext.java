package FinalProject.strategy;

public class CartContext {

    AddToCartStrategy strategy;

    public void setStrategy(AddToCartStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(int amount) {
        strategy.addProductToCart(amount);
    }
}
