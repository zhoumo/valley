package mine.valley.model;

import mine.valley.base.BaseModel;

@SuppressWarnings("serial")
public class Authority extends BaseModel {

	private String userName;

	private String realName;

	public Authority(String userName, String realName) {
		this.userName = userName;
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
