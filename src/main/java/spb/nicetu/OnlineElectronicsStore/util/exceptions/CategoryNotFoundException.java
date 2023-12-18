package spb.nicetu.OnlineElectronicsStore.util.exceptions;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Integer id) {
        super(id, "category");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
