package spb.nicetu.OnlineElectronicsStore.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import spb.nicetu.OnlineElectronicsStore.annotations.UniqueEmail;
import spb.nicetu.OnlineElectronicsStore.services.UserService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.UserNotFoundException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    @Autowired
    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        try {
            userService.findByEmail(email);
        } catch (UserNotFoundException e){
            return true;
        }

        return false;
    }
}
