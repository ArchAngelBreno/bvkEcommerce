package com.bvk.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bvk.util.ConstantError;

@Constraint(validatedBy=CustomerUpdateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateUpdateCustomerInformation {

	String message() default ConstantError.VALIDATION_ERROR;
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
	
}
