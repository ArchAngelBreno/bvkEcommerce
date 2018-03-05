package com.bvk.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bvk.domain.Customer;
import com.bvk.dto.NewCustomerDTO;
import com.bvk.enumerator.CustomerType;
import com.bvk.repository.CustomerRepository;
import com.bvk.resource.exception.FieldMessage;
import com.bvk.util.CpfAndCpnjMask;

public class CustomerValidator implements ConstraintValidator<ValidateCustomerInformation, NewCustomerDTO>{

	@Autowired
	CustomerRepository repo;
	
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
		
		Customer aux = repo.findByEmail(objDto.getEmail());
		
		if (aux!=null) {
			list.add(new FieldMessage("email","Email já existente"));
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
