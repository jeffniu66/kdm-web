package cn.oge.kdmweb.service;

import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.Module;

public interface ModuleService {

	/**
	 * 增加应用包获取所有的应用模块
	 * 
	 * @param app
	 * @return
	 */
	public List<Module> getModules(AppPkg app);

	/**
	 * 根据应用包与模块名称查找模块
	 * 
	 * @param app
	 * @param moduleName
	 * @return
	 */
	public Module getModule(AppPkg app, String moduleName);

	/**
	 * 根据应用包与模块Id查找模块
	 * 
	 * @param app
	 * @param moduleId
	 * @return
	 */
	public Module getModule(AppPkg app, Integer moduleId);

	public List<Module> getModules(String appName);

	public boolean startModule(String appName, String module);

	public boolean startModule_(String appName, Module module);

	public List<String> getStartMessage();

	public String getStartLogMessage();

	public boolean addModule(Module module, AppPkg app);

	public boolean updateModule(Module module, AppPkg app);

	public boolean deleteModule(Module module, AppPkg app);

	public boolean stopModule(String appName, Module module);

}
