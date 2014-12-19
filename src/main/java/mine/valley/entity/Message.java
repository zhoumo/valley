package mine.valley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import mine.valley.base.BaseEntity;

@Entity
@Table(name = "MESSAGE")
@SuppressWarnings("serial")
public class Message extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "SENDER")
	@JsonBackReference
	private User sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVER")
	@JsonBackReference
	private User receiver;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "CONTENT", nullable = false, length = 65535)
	private String content;

	@Column(name = "IS_READ", nullable = false)
	private Boolean isRead = false;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
}
