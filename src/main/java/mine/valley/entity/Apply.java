package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "APPLY")
@SuppressWarnings("serial")
public class Apply extends BaseEntity {

	public enum Type {
		
		PRODUCER("生产者", Short.valueOf("0")),

		AUDITOR("审核者", Short.valueOf("1"));

		private String name;

		private Short code;

		private Type(String name, Short code) {
			this.name = name;
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Short getCode() {
			return code;
		}

		public void setCode(Short code) {
			this.code = code;
		}
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private Job job;

	@Column(name = "TYPE")
	private Short type;

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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
