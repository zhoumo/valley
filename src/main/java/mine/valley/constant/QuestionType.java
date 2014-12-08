package mine.valley.constant;

public enum QuestionType {
	
	SINGLE_SELECTIONS(0), MULTIPLE_SELECTIONS(1), ESSAY_QUESTIONS(2);

	private Short value;

	private QuestionType(Integer value) {
		this.value = value.shortValue();
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
}
