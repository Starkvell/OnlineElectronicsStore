package spb.nicetu.OnlineElectronicsStore.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import spb.nicetu.OnlineElectronicsStore.annotations.UniqueEmail;
import spb.nicetu.OnlineElectronicsStore.services.UserService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.UserNotFoundException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userServiceImpl;

    @Autowired
    public UniqueEmailValidator(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        try {
            userServiceImpl.findByEmail(email);
        } catch (UserNotFoundException e){
            return true;
        }

        return false;
    }
}
