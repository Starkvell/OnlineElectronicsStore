package spb.nicetu.OnlineElectronicsStore.util;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Integer id){
        super("Could not find product " + id);
    }
}
