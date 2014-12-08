package mine.valley.constant;

public enum ApplyType {
	
	CREATOR("生产者", 0), AUDITOR("审核者", 1);

	private String name;

	private Short value;

	private ApplyType(String name, Integer value) {
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
}
