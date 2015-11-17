package cn.oge.kdmweb.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.service.AppPkgConfigService;
import cn.oge.kdmweb.utils.Constants;
import cn.oge.kdmweb.utils.CopyFile;
import cn.oge.kdmweb.utils.FileHelper;
import cn.oge.kdmweb.utils.XStreamHelper;

import com.thoughtworks.xstream.XStream;

@Service("appPkgXmlConfigService")
public class AppPkgXmlConfigServiceImpl implements AppPkgConfigService {

	/** 应用包缓存对象 */
	private List<AppPkg> appCache;
	/** 模块的缓存 */
	private Map<String, List<Module>> modulesCache = new HashMap<String, List<Module>>();

	@Override
	public List<AppPkg> readConfig() {

		if (appCache != null) {
			return appCache;
		}
		String dataPath = Constants.DATA_FILE_PATH;
		appCache = XStreamHelper.xmlToAppList(dataPath);
		return appCache;
	}

	@Override
	public boolean writeConfig(List<AppPkg> appPkgs) {
		// 更新缓存
		this.appCache = appPkgs;
		// 缓存module
		String dataPath = Constants.DATA_FILE_PATH;
		XStream xs = XStreamHelper.getAppPkgXStream();
		boolean result = XStreamHelper.toXmlFileWithHead(xs, appPkgs, dataPath);
		AppPkg app	= new AppPkg();
		 if (appPkgs.size() > 0) {
				app = appPkgs.get(appPkgs.size() - 1);
		} 
		if(modulesCache.containsKey(app.getAppName())){
			cacheModule(app);
		}
		return result;
	}

	@Override
	public boolean writeToModuleConfigXml(MConfigs mconfigs,
			String mconfigInstallPath) {
		XStream xs = XStreamHelper.getMConfigXStream();
		
		return XStreamHelper.toXmlFileWithHead(xs, mconfigs, mconfigInstallPath);
	}

	@Override
	public boolean writeToModuleConfig(List<Module> modules, AppPkg app) {

		boolean result = false;
		String modulePath = (app.getInstallPath() + File.separator + "modules.xml")
				.replace("/", File.separator);

		XStream xs = XStreamHelper.getModulesXStream();
		result = XStreamHelper.toXmlFileWithHead(xs, modules, modulePath);
		// 更新缓存
		if (modulesCache.containsKey(app.getAppName())) {
			cacheModule(app);
		}
		return result;
	}

	@Override
	public List<Module> getModules(AppPkg app) {

		if (modulesCache.containsKey(app.getAppName())) {

			return modulesCache.get(app.getAppName());
		}

		return cacheModule(app);
	}

	/**
	 * 缓存应用包的模块信息
	 * 
	 * @param app
	 * @return
	 */
	private List<Module> cacheModule(AppPkg app) {
		String moduleFile = app.getModuleFile();
		String tmpFile = null;
		String basePath = app.getInstallPath() + File.separator;
		if (moduleFile == null) {
			//判断xml是否存在，不存在则复制过来
			//存在则不变
			boolean result = FileHelper.fileExist(basePath + "modules.xml");
			String oldPath = Constants.MODULES_FILE_PATH;
			String newPath = basePath + "modules.xml";
			if(result){
				tmpFile = basePath + "modules.xml";
			}else{
				CopyFile.copyFile(oldPath, newPath);
				tmpFile = basePath + "modules.xml";
			}
		} 

		File file = new File(tmpFile);
		if (file.exists()) {
			moduleFile = tmpFile.replace("/", File.separator);
		}
		// 返回一个空
		if (moduleFile == null) {
			return new ArrayList<Module>();
		}

		List<Module> moduleList = XStreamHelper.xmlToModuleList(moduleFile);
		for (Module module : moduleList) {

			/** 配置逻辑关系 */
			// 关联模块
			module.setAppPkg(app);
			module.setAppId(app.getAppId());

			// 模块的完整路路径
			String appPath = app.getInstallPath();
			String modulePath = module.getInstallPath();
			module.setFullPath(getModuleFullPath(appPath, modulePath));

			// 缓存模块的配置信息
			cacheModuleConfig(module);
		}
		// 放入缓存
		modulesCache.put(app.getAppName(), moduleList);

		return moduleList;
	}

	/**
	 * 缓存模块的配置信息
	 * 
	 * @param module
	 */
	private MConfigs cacheModuleConfig(Module module) {

		String modulePath = module.getFullPath();
		String mcfgFilePath = modulePath;
		if (!(modulePath.endsWith("/") || modulePath.endsWith("\\"))) {
			mcfgFilePath += File.separator;
		}
		mcfgFilePath += "mconfig.xml";
		mcfgFilePath.replace("/", File.separator);
		File mcfgFile = new File(mcfgFilePath);
		MConfigs mconfigs = null;
		if (mcfgFile.exists()) {
			mconfigs = XStreamHelper.xmlToMconfigsList(mcfgFilePath);
		} else {
			mconfigs = new MConfigs();
		}

		mconfigs.setAppPkg(module.getAppPkg());
		mconfigs.setModule(module);
		module.setMconfigs(mconfigs);

		return mconfigs;
	}

	/**
	 * 通过应用包路径，和模块安装路径，获取模块的完整路径<br>
	 * 
	 * @param appPath
	 * @param modulePath
	 *            可能是配置了相对路径
	 * @return
	 */
	private String getModuleFullPath(String appPath, String modulePath) {

		// 相对路径
		if (modulePath.startsWith("./") || modulePath.startsWith(".\\")) {
			// 应用包路径结尾不带路径分隔符，则加上
			if (!(appPath.endsWith("/") || appPath.endsWith("\\"))) {
				appPath += File.separator;
			}
			return appPath + modulePath.substring(2);
		}

		// 绝对路径
		return modulePath;
	}

	@Override
	public MConfigs getMconfigs(String dataPath) {
		return XStreamHelper.xmlToMconfigsList(dataPath);
	}

	class IdComparator implements Comparator<AppPkg> {

		// Collections.sort(appList, new IdComparator());
		@Override
		public int compare(AppPkg o1, AppPkg o2) {
			return o2.getAppId() - o1.getAppId();
		}

	}

}
