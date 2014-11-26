package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "QUESTION")
@SuppressWarnings("serial")
public class Question extends BaseEntity {

	public static final Short TYPE_SINGLE_SELECTIONS = 0;

	public static final Short TYPE_MULTIPLE_SELECTIONS = 1;

	public static final Short TYPE_ESSAY_QUESTIONS = 2;

	@Column(name = "QUESTION_CONTENT", nullable = false, length = 65535)
	private String questionContent;

	@Column(name = "QUESTION_ANSWER", nullable = false, length = 65535)
	private String questionAnswer;

	@Column(name = "QUESTION_LEVEL", nullable = false)
	private Short questionLevel;

	@Column(name = "QUESTION_TYPE", nullable = false)
	private Short questionType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAPER_ID")
	private Paper paper;

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Short getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(Short questionLevel) {
		this.questionLevel = questionLevel;
	}

	public Short getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Short questionType) {
		this.questionType = questionType;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
