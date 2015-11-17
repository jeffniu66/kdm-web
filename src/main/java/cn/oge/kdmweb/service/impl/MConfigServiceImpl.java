package cn.oge.kdmweb.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MCItem;
import cn.oge.kdmweb.domain.MConfig;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.service.AppPkgConfigService;
import cn.oge.kdmweb.service.AppPkgService;
import cn.oge.kdmweb.service.MConfigService;
import cn.oge.kdmweb.service.ModuleService;
import cn.oge.kdmweb.utils.FileHelper;
import cn.oge.kdmweb.utils.ProUtil;
import cn.oge.kdmweb.utils.XmlReadAndWrite;

@Service("mconfigService")
public class MConfigServiceImpl implements MConfigService {

	@Autowired
	private AppPkgConfigService config;
	
	@Autowired
	private AppPkgService appPkgServce;

	@Autowired
	private AppPkgConfigService appPkgConfigServce;

	@Autowired
	private ModuleService moduleService;

	@Override
	public MConfigs getMConfigs(AppPkg pkg, Module module) {

		MConfigs mconfigs = module.getMconfigs();
		if (mconfigs != null) {
			return mconfigs;
		}
		
		mconfigs = new MConfigs();
		mconfigs.setAppPkg(pkg);
		mconfigs.setModule(module);

		return mconfigs;
	}

	@Override
	public MConfigs getMConfigs(String dataPath) {
		return appPkgConfigServce.getMconfigs(dataPath);

	}

	@Override
	public MConfigs getMConfigs(Integer appId, Integer moduleId) {
		AppPkg app = appPkgServce.findAppById(appId);
		Module module = moduleService.getModule(app, moduleId);
		return getMConfigs(app, module);
	}

	@Override
	public MConfigs getMConfigs(String appName, String moduleName) {
		AppPkg app = appPkgServce.getAppPkg(appName);
		Module module = moduleService.getModule(app, moduleName);
		return getMConfigs(app, module);
	}

	@Override
	public MConfigs getMConfigsProper(String app_installPath,
			String module_installPath, MConfigs mConfigs) {
		String propPath = "";
		if(mConfigs.getCfgList()!=null){
			List<MConfig> mconfigList = mConfigs.getCfgList();
			for (MConfig mconfig : mconfigList) {
				propPath = mconfig.getFilePath();
				String _propPath = FileHelper.getFilePath(app_installPath,
						module_installPath, propPath);
				mconfig.setFilePath(_propPath);
				List<MCItem> mcitemList = mconfig.getItemList();
				for (MCItem mcitem : mcitemList) {
					String keyName = mcitem.getKeyName();
					String keyValue = ProUtil.getPropertyValue(keyName, _propPath);
					mcitem.setKeyName(keyName);
					mcitem.setKeyValue(keyValue);
				}
				mconfig.setItemList(mcitemList);
			}
			mConfigs.setCfgList(mconfigList);
		}
		return mConfigs;
	}

	@Override
	public boolean updateMConfigs(MConfigs mconfigs, String mconfigInstallPath) {
		
		return config.writeToModuleConfigXml(mconfigs, mconfigInstallPath);
	}
	
	@Override
	public String readFromFile(String filePath) {
		String str = "";
		try {
			str = XmlReadAndWrite.readFromFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public boolean writeToFile(String filePath, String content) {
		try {
			 XmlReadAndWrite.writeToFile(filePath, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
