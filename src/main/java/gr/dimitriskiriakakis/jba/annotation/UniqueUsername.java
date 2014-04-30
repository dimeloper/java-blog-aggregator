package gr.dimitriskiriakakis.jba.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Payload;

import javax.validation.Constraint;


@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueUsernameValidator.class })
public @interface UniqueUsername {
	
	String message(); 
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
