package cn.oge.kdmweb.domain;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 模块开放的配置信息
 * 
 * @author jimcoly
 *
 */
public class MConfigs {

	/** 配置所在的应用包信息 */
	@XStreamOmitField
	private AppPkg appPkg;
	/** 配置所在的应用模块信息 */
	@XStreamOmitField
	private Module module;

	private List<MConfig> cfgList = new ArrayList<MConfig>();

	public List<MConfig> getCfgList() {
		return cfgList;
	}

	public void setCfgList(List<MConfig> cfgList) {
		this.cfgList = cfgList;
	}

	public AppPkg getAppPkg() {
		return appPkg;
	}

	public void setAppPkg(AppPkg appPkg) {
		this.appPkg = appPkg;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
