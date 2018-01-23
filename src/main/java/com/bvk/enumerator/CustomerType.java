package com.bvk.enumerator;

public enum CustomerType {
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Juridica");

	private int cod;
	private String desc;
	
	private CustomerType(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static CustomerType toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for (CustomerType x : CustomerType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+cod);
		
	}
	
}
