package br.com.hoptech.vaaguruapi.enums;

public enum ScheduleStatus {
	
	ADMIN(1, "ROLE_ADMIN"),
	ROWER(2, "ROLE_ROWER");
    
	private Integer code;
	private String name;
	
	private ScheduleStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
		
	public static ScheduleStatus toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
				
		for (ScheduleStatus type : ScheduleStatus.values()) {
			if (code.equals(type.getCode())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
