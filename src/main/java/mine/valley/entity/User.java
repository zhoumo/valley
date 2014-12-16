package mine.valley.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mine.valley.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
@SuppressWarnings("serial")
public class User extends BaseEntity {

	@Column(name = "LOGIN_NAME", nullable = false)
	private String loginName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "REAL_NAME", nullable = false)
	private String realName;

	@Column(name = "TYPE", nullable = false, columnDefinition = "TINYINT")
	private Short type;

	@Column(name = "ENABLED", nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean enabled = true;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ACCESS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;

	@Transient
	private List<Apply> applyCreator;

	@Transient
	private List<Apply> applyAuditor;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Apply> getApplyCreator() {
		return applyCreator;
	}

	public void setApplyCreator(List<Apply> applyCreator) {
		this.applyCreator = applyCreator;
	}

	public List<Apply> getApplyAuditor() {
		return applyAuditor;
	}

	public void setApplyAuditor(List<Apply> applyAuditor) {
		this.applyAuditor = applyAuditor;
	}
}
