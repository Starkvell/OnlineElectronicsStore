package spb.nicetu.OnlineElectronicsStore.util.exceptions;


public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(Integer id) {
        super(id, "order");
    }

    public OrderNotFoundException(String message){
        super(message);
    }
}
