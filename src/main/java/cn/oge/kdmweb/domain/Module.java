package cn.oge.kdmweb.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Module implements Serializable {

	private static final long serialVersionUID = 770732537917321547L;
	/** 应用模块所在的应用包信息 */
	@XStreamOmitField
	private AppPkg appPkg;

	private Integer mid;
	/** 所属app应用 */
	private Integer appId;
	/** 模块名称 */
	private String moduleName;
	/** 安装路径 */
	private String installPath;
	/** 模块的完整路路径 */
	@XStreamOmitField
	private String fullPath;
	/** 模块描述 */
	private String descs;
	/** 启动脚本 */
	private String startScript;
	/** 停止脚本 */
	private String stopScript;
	@XStreamOmitField
	private MConfigs mconfigs;
	/** 模块启停状态：0-停止，1-启动 */
	@XStreamOmitField
	private Integer status;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getStartScript() {
		return startScript;
	}

	public void setStartScript(String startScript) {
		this.startScript = startScript;
	}

	public String getStopScript() {
		return stopScript;
	}

	public void setStopScript(String stopScript) {
		this.stopScript = stopScript;
	}

	public String getInstallPath() {
		return installPath;
	}

	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}

	public MConfigs getMconfigs() {
		return mconfigs;
	}

	public void setMconfigs(MConfigs mconfigs) {
		this.mconfigs = mconfigs;
	}

	public AppPkg getAppPkg() {
		return appPkg;
	}

	public void setAppPkg(AppPkg appPkg) {
		this.appPkg = appPkg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

}
