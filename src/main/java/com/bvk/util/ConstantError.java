package com.bvk.util;

import java.io.Serializable;

public class ConstantError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String VALIDATION_ERROR= "Erro de validação";
	public static final String DELETE_CUSTOMER_ERROR="Não é possivel excluir um cliente que possui pedidos";
	public static final String DELETE_CATEGORY_ERROR= "Não é possivel excluir uma categoria que possui produtos";
	public static final String OBJECT_NOT_FOUND_ERROR = "Não foi encontrado nenhum id: ";
	
}
