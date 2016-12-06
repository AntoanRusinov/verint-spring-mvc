package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Component
@NotBlank
public @interface ValidPhone {

	String message() default "{Phone}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}