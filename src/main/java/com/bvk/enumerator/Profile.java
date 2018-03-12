package com.bvk.enumerator;

public enum Profile {

	ADMIN(1,"ROLE_ADMIN"),
	CLIENT(2,"ROLE_CLIENT");

	private int cod;
	private String desc;
	
	private Profile(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Profile toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for (Profile x : Profile.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+cod);
		
	}

	
}
