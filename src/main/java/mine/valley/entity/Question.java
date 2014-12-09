package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "QUESTION")
@SuppressWarnings("serial")
public class Question extends BaseEntity {

	@Column(name = "QUESTION", nullable = false, length = 65535)
	private String question;

	@Column(name = "ANSWER", nullable = false, length = 65535)
	private String answer;

	@Column(name = "DIFFICULTY", nullable = false)
	private Short difficulty = 0;

	@Column(name = "AUDIT", nullable = false)
	private Boolean audit = false;

	@Column(name = "COMMENT", nullable = true, length = 65535)
	private String comment;

	@Column(name = "TYPE", nullable = false)
	private Short type;

	@JsonIgnore
	@ManyToOne
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

	public Short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Short difficulty) {
		this.difficulty = difficulty;
	}

	public Boolean getAudit() {
		return audit;
	}

	public void setAudit(Boolean audit) {
		this.audit = audit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
