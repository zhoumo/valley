package mine.valley.model;

import mine.valley.base.BaseModel;

@SuppressWarnings("serial")
public class Authority extends BaseModel {

	private String loginName;

	private String realName;

	public Authority(String loginName, String realName) {
		this.loginName = loginName;
		this.realName = realName;
	}

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
}
