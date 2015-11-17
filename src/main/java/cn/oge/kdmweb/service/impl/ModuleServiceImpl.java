package cn.oge.kdmweb.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.service.AppPkgConfigService;
import cn.oge.kdmweb.service.AppPkgService;
import cn.oge.kdmweb.service.ModuleService;
import cn.oge.kdmweb.utils.FileHelper;
import cn.oge.kdmweb.utils.SystemOperateHelper;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private AppPkgConfigService config;
	@Autowired
	private AppPkgService appPkgService;

	@Override
	public boolean deleteModule(Module module, AppPkg app) {
		Integer mid = module.getMid();
		List<Module> modules = getModules(app.getAppName());
		for (Module _module : modules) {
			if (mid.equals(_module.getMid())) {
				modules.remove(_module);
				return config.writeToModuleConfig(modules, app);
			}
		}
		return false;
	}

	@Override
	public boolean startModule_(String appName, Module module) {
		boolean result = false;
		AppPkg app = appPkgService.getAppPkg(appName);
		String app_installPath = app.getInstallPath();
		String module_installPath = module.getInstallPath().substring(2).trim();
		String start = module.getStartScript();
		if (start != null) {
			String[] str = start.split("/");
			String path = app_installPath + File.separator + module_installPath
					+ File.separator + str[0].trim();
			String cmd = path + File.separator + str[1].trim();
			File file = new File(path);
			result = SystemOperateHelper.startModule(cmd, file);
		}
		return result;

	}

	@Override
	public boolean stopModule(String appName, Module module) {
		boolean result = false;
		AppPkg app = appPkgService.getAppPkg(appName);
		String app_installPath = app.getInstallPath();
		String module_installPath = module.getInstallPath().substring(2).trim();
		String stop = module.getStopScript();
		if (stop != null) {
			String[] str = stop.split("/");
			String path = (app_installPath + File.separator
					+ module_installPath + File.separator + str[0].trim())
					.replace("/", File.separator);
			String cmd = path + File.separator + str[1].trim();
			File file = new File(path);
			if (FileHelper.fileExist(cmd)) {
				result = SystemOperateHelper.stopModule(cmd, file);
			}
		}

		return result;
	}

	@Override
	public List<String> getStartMessage() {
		return SystemOperateHelper.getMessage();
	}

	@Override
	public String getStartLogMessage() {
		return SystemOperateHelper.getstartMessage();
	}

	@Override
	public Module getModule(AppPkg app, String moduleName) {
		List<Module> modules = config.getModules(app);
		for (Module module : modules) {
			if (moduleName.equals(module.getModuleName())) {
				return module;
			}
		}
		return null;
	}

	@Override
	public Module getModule(AppPkg app, Integer moduleId) {
		List<Module> modules = config.getModules(app);
		for (Module module : modules) {
			if (moduleId.equals(module.getMid())) {
				return module;
			}
		}
		return null;
	}

	@Override
	public boolean updateModule(Module module, AppPkg app) {
		Integer mid = module.getMid();
		List<Module> modules = getModules(app.getAppName());
		for (Module _module : modules) {
			if (mid.equals(_module.getMid())) {
				_module.setAppId(module.getAppId());
				_module.setDescs(module.getDescs());
				_module.setInstallPath(module.getInstallPath());
				_module.setModuleName(module.getModuleName());
				_module.setStartScript(module.getStartScript());
				_module.setStopScript(module.getStopScript());
				_module.setMconfigs(module.getMconfigs());

				return config.writeToModuleConfig(modules, app);
			}
		}
		return false;
	}

	@Override
	public List<Module> getModules(String appName) {
		return getModules(appPkgService.getAppPkg(appName));
	}

	@Override
	public List<Module> getModules(AppPkg app) {
		return config.getModules(app);
	}

	@Override
	public boolean startModule(String appName, String Module) {
		return false;
	}

	@Override
	public boolean addModule(Module module, AppPkg app) {
		List<Module> modules = getModules(app.getAppName());
		// 维护mid
		if (modules.size() > 0) {
			Module lastModule = modules.get(modules.size() - 1);
			module.setMid(lastModule.getMid() + 1);
		} else {
			module.setMid(1);
		}
		modules.add(module);
		return config.writeToModuleConfig(modules, app);
	}

}
