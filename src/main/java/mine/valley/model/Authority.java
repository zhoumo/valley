package mine.valley.model;

import mine.valley.base.BaseModel;

@SuppressWarnings("serial")
public class Authority extends BaseModel {

	private String loginName;

	private String realName;

	private Boolean applyCreator;

	private Boolean applyAuditor;

	private Boolean approveCreator;

	private Boolean approveAuditor;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Boolean getApplyCreator() {
		return applyCreator;
	}

	public void setApplyCreator(Boolean applyCreator) {
		this.applyCreator = applyCreator;
	}

	public Boolean getApplyAuditor() {
		return applyAuditor;
	}

	public void setApplyAuditor(Boolean applyAuditor) {
		this.applyAuditor = applyAuditor;
	}

	public Boolean getApproveCreator() {
		return approveCreator;
	}

	public void setApproveCreator(Boolean approveCreator) {
		this.approveCreator = approveCreator;
	}

	public Boolean getApproveAuditor() {
		return approveAuditor;
	}

	public void setApproveAuditor(Boolean approveAuditor) {
		this.approveAuditor = approveAuditor;
	}
}
