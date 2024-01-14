package spb.nicetu.OnlineElectronicsStore.annotations;

import spb.nicetu.OnlineElectronicsStore.util.validators.ProductIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductIdValidator.class)
@Documented
public @interface ExistingProductId {

    String message() default "Product with this ID does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
