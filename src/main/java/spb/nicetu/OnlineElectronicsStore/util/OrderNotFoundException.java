package spb.nicetu.OnlineElectronicsStore.util;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Integer id){
        super("Could not find order " + id);
    }
}
