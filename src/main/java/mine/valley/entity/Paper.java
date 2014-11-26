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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "PAPER")
@SuppressWarnings("serial")
public class Paper extends BaseEntity {

	@Column(name = "PAPER_NAME", nullable = false)
	private String paperName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTHOR")
	private User author;

	@OneToMany(mappedBy = "paper", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<Question> questions;

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
