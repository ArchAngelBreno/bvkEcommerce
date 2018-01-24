package com.bvk.enumerator;

public enum PaymentStatus {

	PENDENTE(1,"Pendente"),
	QUITADO(2,"Concluido"),
	CANCELADO(3,"Cancelado");

	private int cod;
	private String desc;
	
	private PaymentStatus(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for (PaymentStatus x : PaymentStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+cod);
		
	}

	
}
