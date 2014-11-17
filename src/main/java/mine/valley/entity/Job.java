package mine.valley.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mine.valley.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "JOB")
@SuppressWarnings("serial")
public class Job extends BaseEntity {

	@Column(name = "JOB_NAME", nullable = false)
	private String jobName;

	@Column(name = "JOB_LEVEL", nullable = false)
	private Integer jobLevel = 0;

	@JsonBackReference
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private List<Job> children;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "P_ID")
	private Job parent;

	@Transient
	private Long parentId;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public List<Job> getChildren() {
		return children;
	}

	public void setChildren(List<Job> children) {
		this.children = children;
	}

	public Job getParent() {
		return parent;
	}

	public void setParent(Job parent) {
		this.parent = parent;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
