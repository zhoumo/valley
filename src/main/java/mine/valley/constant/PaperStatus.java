package mine.valley.constant;

public enum PaperStatus {
	
	CREATE(0), AUDIT_NO(1), AUDIT_YES(2);

	private Short value;

	private PaperStatus(Integer value) {
		this.value = value.shortValue();
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
}
