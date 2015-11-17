package cn.oge.kdmweb.service;

import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;

public interface AppPkgService {

	public AppPkg findAppById(Integer appId);

	public AppPkg getAppPkg(String appName);

	public List<AppPkg> getAppPkgs();

	public boolean addAppPkg(AppPkg app);

	public boolean updateAppPkg(AppPkg app);

	public boolean deleteAppPkg(AppPkg app);
	
}
