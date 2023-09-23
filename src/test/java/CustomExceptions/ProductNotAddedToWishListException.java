package CustomExceptions;

public class ProductNotAddedToWishListException extends Exception {
    public ProductNotAddedToWishListException(String errorMessage) {
        super(errorMessage);
    }
}
