package cn.oge.kdmweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.service.AppPkgConfigService;
import cn.oge.kdmweb.service.AppPkgService;

@Service("appPkgService")
public class AppPkgServiceImpl implements AppPkgService {

	@Autowired
	private AppPkgConfigService config;

	@Override
	// TODO 从XML中获取
	public AppPkg getAppPkg(String appName) {
		List<AppPkg> apps = getAppPkgs();
		for (AppPkg app : apps) {
			if (appName.equals(app.getAppName())) {
				return app;
			}
		}
		return null;
	}

	@Override
	public List<AppPkg> getAppPkgs() {
		return config.readConfig();
	}

	@Override
	public boolean addAppPkg(AppPkg app) {
		List<AppPkg> appPkgs = getAppPkgs();
		// List是有序的集合
		// 维护appId
		if (appPkgs.size() > 0) {
			AppPkg lastPkg = appPkgs.get(appPkgs.size() - 1);
			app.setAppId(lastPkg.getAppId() + 1);
		} else {
			app.setAppId(1);
		}
		appPkgs.add(app);

		return config.writeConfig(appPkgs);
	}

	@Override
	public AppPkg findAppById(Integer appId) {

		List<AppPkg> appPkgs = getAppPkgs();

		for (AppPkg app : appPkgs) {
			if (appId.equals(app.getAppId())) {
				return app;
			}
		}
		return null;
	}

	@Override
	public boolean updateAppPkg(AppPkg app) {
		Integer appId = app.getAppId();
		List<AppPkg> appPkgs = getAppPkgs();

		for (AppPkg _app : appPkgs) {
			if (appId.equals(_app.getAppId())) {

				_app.setAppName(app.getAppName());
				_app.setDesc(app.getDesc());
				_app.setInstallPath(app.getInstallPath());
				_app.setModuleFile(app.getModuleFile());

				return config.writeConfig(appPkgs);
			}
		}
		return false;
	}

	@Override
	public boolean deleteAppPkg(AppPkg app) {
		Integer appId = app.getAppId();
		List<AppPkg> appPkgs = getAppPkgs();
		for (AppPkg _app : appPkgs) {
			if (appId.equals(_app.getAppId())) {
				appPkgs.remove(_app);
				return config.writeConfig(appPkgs);
			}
		}
		return false;
	}

}
