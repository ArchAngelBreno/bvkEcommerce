package com.bvk.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bvk.dto.NewCustomerDTO;
import com.bvk.enumerator.CustomerType;
import com.bvk.resource.exception.FieldMessage;
import com.bvk.util.CpfAndCpnjMask;

public class CustomerValidator implements ConstraintValidator<ValidateCustomerInformation, NewCustomerDTO>{

	@Override
	public void initialize(ValidateCustomerInformation ann) {
		
	}

	@Override
	public boolean isValid(NewCustomerDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getCustomerType().equals(CustomerType.PESSOAFISICA.getCod()) && 
				!CpfAndCpnjMask.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj","CPF Inválido"));
		}
		
		if (objDto.getCustomerType().equals(CustomerType.PESSOAJURIDICA.getCod()) && 
				!CpfAndCpnjMask.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj","CNPJ Inválido"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getField())
			.addConstraintViolation();
			
		}
		return list.isEmpty();
	}

}
