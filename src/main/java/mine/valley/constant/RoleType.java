package mine.valley.constant;

public enum RoleType {
	
	USER("ROLE_USER", 0), ADMIN("ROLE_ADMIN", 1), IT_ENGINEER("ROLE_IT_ENGINEER", 2), HR("ROLE_HR", 3), HEADHUNTER("ROLE_HEADHUNTER", 4);

	private String name;

	private Short value;

	private RoleType(String name, Integer value) {
		this.name = name;
		this.value = value.shortValue();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static String getRoleName(Short value) {
		switch (value) {
			case 0:
				return RoleType.USER.getName();
			case 1:
				return RoleType.ADMIN.getName();
			case 2:
				return RoleType.IT_ENGINEER.getName();
			case 3:
				return RoleType.HR.getName();
			case 4:
				return RoleType.HEADHUNTER.getName();
			default:
				return null;
		}
	}
}
