package mine.valley.base;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseEntity extends BaseModel {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@JsonIgnore
	@Column(name = "IS_DELETED", nullable = false, columnDefinition = "BIT DEFAULT 0")
	protected Boolean isDeleted = false;

	@Column(name = "CREATE_TIME", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	protected Timestamp createTime;

	@Column(name = "UPDATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateTime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime() {
		this.createTime = new Timestamp(System.currentTimeMillis());
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
