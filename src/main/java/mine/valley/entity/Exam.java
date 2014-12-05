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

	public static final Short START = 1;

	public static final Short END = 2;

	@ManyToOne
	@JoinColumn(name = "USER")
	private User user;

	@ManyToOne
	@JoinColumn(name = "PAPER")
	private Paper paper;

	@Column(name = "STATUS", nullable = false)
	private Short status = 0;

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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
