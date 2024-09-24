package spb.nicetu.OnlineElectronicsStore.util.exceptions;

public class CartNotFoundException extends NotFoundException{
    public CartNotFoundException(Integer id) {
        super(id, "cart");
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
