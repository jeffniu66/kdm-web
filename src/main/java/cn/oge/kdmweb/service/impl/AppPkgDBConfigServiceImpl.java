package cn.oge.kdmweb.service.impl;

import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MConfig;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.service.AppPkgConfigService;

public class AppPkgDBConfigServiceImpl implements AppPkgConfigService {

	@Override
	public List<AppPkg> readConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeConfig(List<AppPkg> appPkgs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Module> getModules(AppPkg app) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MConfigs getMconfigs(String dataPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeToModuleConfig(List<Module> modules, AppPkg app) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeToModuleConfigXml(MConfigs mconfigs,
			String app_installPath) {
		// TODO Auto-generated method stub
		return false;
	}

}
