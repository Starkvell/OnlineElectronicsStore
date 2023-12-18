package spb.nicetu.OnlineElectronicsStore.util.exceptions;


public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Integer id) {
        super(id, "product");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
