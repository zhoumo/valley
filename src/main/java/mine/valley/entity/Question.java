package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "QUESTION")
@SuppressWarnings("serial")
public class Question extends BaseEntity {

	public static final Short TYPE_SINGLE_SELECTIONS = 0;

	public static final Short TYPE_MULTIPLE_SELECTIONS = 1;

	public static final Short TYPE_ESSAY_QUESTIONS = 2;

	@Column(name = "QUESTION", nullable = false, length = 65535)
	private String question;

	@Column(name = "ANSWER", nullable = false, length = 65535)
	private String answer;

	@Column(name = "LEVEL", nullable = false)
	private Short level;

	@Column(name = "TYPE", nullable = false)
	private Short type;

	@JsonIgnore
	@JoinColumn(name = "PAPER_ID")
	private Paper paper;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
