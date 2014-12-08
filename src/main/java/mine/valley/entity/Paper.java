package mine.valley.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mine.valley.base.BaseEntity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "PAPER")
@SuppressWarnings("serial")
public class Paper extends BaseEntity {

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TIME", nullable = false)
	private Integer time = 90;

	@Column(name = "STATUS", nullable = false, columnDefinition = "TINYINT")
	private Short status;

	@ManyToOne
	@JoinColumn(name = "AUTHOR")
	private User author;

	@ManyToOne
	@JoinColumn(name = "JOB")
	private Job job;

	@OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Question> questions;

	@Transient
	private Long selectJobId;

	@Transient
	private Short examStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Long getSelectJobId() {
		return selectJobId;
	}

	public void setSelectJobId(Long selectJobId) {
		this.selectJobId = selectJobId;
	}

	public Short getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(Short examStatus) {
		this.examStatus = examStatus;
	}
}
