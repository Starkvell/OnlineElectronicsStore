package spb.nicetu.OnlineElectronicsStore.util;

public abstract class NotFoundException extends RuntimeException{
    public NotFoundException(Integer id, String object){
        super("Could not find object " + object + " with ID:"+ id);
    }

    public NotFoundException(String message){
        super(message);
    }
}
