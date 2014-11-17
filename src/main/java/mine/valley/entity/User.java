package mine.valley.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import mine.valley.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
@SuppressWarnings("serial")
public class User extends BaseEntity {

	public static final short USER_TYPE_ADMIN = 0;

	public static final short USER_TYPE_IT_ENGINEER = 1;

	public static final short USER_TYPE_HR = 2;

	public static final short USER_TYPE_HEADHUNTER = 3;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "REAL_NAME", nullable = false)
	private String realName;

	@Column(name = "USER_TYPE", nullable = false, columnDefinition = "TINYINT")
	private Short userType;

	@Column(name = "ENABLED", nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean enabled = true;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
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

	public static String getRoleName(Short type) {
		switch (type) {
		case USER_TYPE_IT_ENGINEER:
			return Role.ROLE_IT_ENGINEER;
		case USER_TYPE_HR:
			return Role.ROLE_HR;
		case USER_TYPE_HEADHUNTER:
			return Role.ROLE_HEADHUNTER;
		default:
			return Role.ROLE_ADMIN;
		}
	}
}
