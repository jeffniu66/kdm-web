package cn.oge.kdmweb.service;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;

public interface MConfigService {

	public MConfigs getMConfigs(Integer appId, Integer moduleId);

	public MConfigs getMConfigs(String appName, String moduleName);

	public MConfigs getMConfigs(AppPkg pkg, Module module);

	public MConfigs getMConfigs(String dataPath);

	public boolean updateMConfigs(MConfigs mconfigs, 
			String moduleInstallPath);
	
	public MConfigs getMConfigsProper(String app_installPath,
			String module_installPath, MConfigs mConfigs);

	public String  readFromFile(String filePath);
	
	public boolean  writeToFile(String filePath, String content);
}
