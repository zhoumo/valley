package mine.valley.model;

import mine.valley.base.BaseModel;

@SuppressWarnings("serial")
public class Authority extends BaseModel {

	private String loginName;

	private String realName;

	private Boolean hasAppliedProducer;

	private Boolean hasAppliedAuditor;

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

	public Boolean getHasAppliedProducer() {
		return hasAppliedProducer;
	}

	public void setHasAppliedProducer(Boolean hasAppliedProducer) {
		this.hasAppliedProducer = hasAppliedProducer;
	}

	public Boolean getHasAppliedAuditor() {
		return hasAppliedAuditor;
	}

	public void setHasAppliedAuditor(Boolean hasAppliedAuditor) {
		this.hasAppliedAuditor = hasAppliedAuditor;
	}
}
