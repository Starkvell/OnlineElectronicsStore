package spb.nicetu.OnlineElectronicsStore.util;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException(Integer id) {
        super(id, "user");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
