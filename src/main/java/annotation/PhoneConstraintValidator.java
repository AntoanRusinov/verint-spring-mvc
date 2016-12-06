package annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// creating my custom own annotation
public class PhoneConstraintValidator implements ConstraintValidator<ValidPhone, String> {

	@Override
	public void initialize(ValidPhone arg) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String phoneField, ConstraintValidatorContext context) {
		return phoneField.matches("[0-9()-\\.]*");
	}

}