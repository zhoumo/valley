package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "EXAM")
@SuppressWarnings("serial")
public class Exam extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "USER")
	private User user;

	@ManyToOne
	@JoinColumn(name = "PAPER")
	private Paper paper;

	@Column(name = "ANSWER", length = 65535)
	private String answer;

	@Column(name = "STATUS", nullable = false)
	private Short status;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
