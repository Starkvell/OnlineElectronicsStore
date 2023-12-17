package spb.nicetu.OnlineElectronicsStore.util;

public class OrderNotFoundException extends NotFoundException{
    public OrderNotFoundException(Integer id) {
        super(id, "order");
    }
}
