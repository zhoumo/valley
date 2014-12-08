package mine.valley.constant;

public enum ExamStatus {
	
	START(0), END(1);

	private Short value;

	private ExamStatus(Integer value) {
		this.value = value.shortValue();
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
}
