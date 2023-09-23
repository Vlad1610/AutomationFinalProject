package CustomExceptions;

public class ProductNotAddedToCartException extends Exception{
    public ProductNotAddedToCartException(String errorMessage) {
        super(errorMessage);
    }
}
