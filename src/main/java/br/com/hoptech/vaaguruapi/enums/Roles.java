package br.com.hoptech.vaaguruapi.enums;

public enum Roles {
	
	ADMIN(1, "ROLE_ADMIN"),
	ROWER(2, "ROLE_ROWER");
    
	private Integer code;
	private String name;
	
	private Roles(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
		
	public static Roles toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
				
		for (Roles type : Roles.values()) {
			if (code.equals(type.getCode())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
