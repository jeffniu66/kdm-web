package cn.oge.kdmweb.service;

import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;

public interface AppPkgConfigService {

	public List<AppPkg> readConfig();

	public MConfigs getMconfigs(String dataPath);

	public boolean writeConfig(List<AppPkg> appPkgs);

	public List<Module> getModules(AppPkg app);

	public boolean writeToModuleConfigXml(MConfigs mconfigs,
			String app_installPath);

	public boolean writeToModuleConfig(List<Module> modules, AppPkg app);

}
