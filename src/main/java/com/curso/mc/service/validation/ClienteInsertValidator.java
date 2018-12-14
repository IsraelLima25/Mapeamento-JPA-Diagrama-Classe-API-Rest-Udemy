package com.curso.mc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.curso.mc.domain.enuns.TipoCliente;
import com.curso.mc.dto.ClienteNewDto;
import com.curso.mc.resources.exceptions.FieldMessage;
import com.curso.mc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
	List<FieldMessage> list = new ArrayList<>();
	// inclua os testes aqui, inserindo erros na lista
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
		list.add(new FieldMessage("cpfOuCnpj","Cpf Inválido"));
	}
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
		list.add(new FieldMessage("cpfOuCnpj","Cpnj Inválido"));
	}
	
	
	for (FieldMessage e : list) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(e.getMessage())
	.addPropertyNode(e.getFieldName()).addConstraintViolation();
	}
	return list.isEmpty();
	}

}
