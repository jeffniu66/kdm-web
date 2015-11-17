package cn.oge.kdmweb.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class AppPkg implements Serializable {

	private static final long serialVersionUID = 6746497481502711489L;

	private Integer appId;
	private String appName;
	private String desc;
	private String installPath;
	@XStreamOmitField
	private String moduleFile;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getInstallPath() {
		return installPath;
	}

	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}

	public String getModuleFile() {
		return moduleFile;
	}

	public void setModuleFile(String moduleFile) {
		this.moduleFile = moduleFile;
	}

}
