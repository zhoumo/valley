package mine.valley.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "LEVEL", nullable = false)
	private Integer level = 0;

	@JsonBackReference
	@OneToMany(mappedBy = "parent")
	private List<Job> children;

	@ManyToOne
	@JoinColumn(name = "P_ID")
	private Job parent;

	@Transient
	private Long parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
