package spb.nicetu.OnlineElectronicsStore.util.validators;

import spb.nicetu.OnlineElectronicsStore.annotations.ExistingProductId;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ExistingProductId, Integer> {

    private final ProductService productServiceImpl;

    public ProductIdValidator(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }



    @Override
    public void initialize(ExistingProductId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer productId, ConstraintValidatorContext constraintValidatorContext) {
        return productId != null && productServiceImpl.existsById(productId);
    }
}
