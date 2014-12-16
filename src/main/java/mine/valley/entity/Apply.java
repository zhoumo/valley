package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "APPLY")
@SuppressWarnings("serial")
public class Apply extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private Job job;

	@Column(name = "TYPE", nullable = false)
	private Short type;

	@Column(name = "APPROVED")
	private Boolean approved;

	@Column(name = "RESUME", nullable = false, length = 65535)
	private String resume;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
