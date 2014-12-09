package mine.valley.constant;

public enum PaperStatus {
	
	CREATE(0), SUBMIT(1), AUDIT_NO(2), AUDIT_YES(3);

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
