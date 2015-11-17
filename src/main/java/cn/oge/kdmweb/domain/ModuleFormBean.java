package cn.oge.kdmweb.domain;

import java.io.Serializable;

public class ModuleFormBean implements Serializable{
	private static final long serialVersionUID = -1318372542116200387L;
	private Integer mappId;

	private Module module;
	
	public Integer getMappId() {
		return mappId;
	}
	public void setMappId(Integer mappId) {
		this.mappId = mappId;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	

	
}
