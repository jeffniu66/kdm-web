package cn.oge.kdmweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MCItem;
import cn.oge.kdmweb.domain.MConfig;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.service.AppPkgService;
import cn.oge.kdmweb.service.MConfigService;
import cn.oge.kdmweb.service.ModuleService;
import cn.oge.kdmweb.utils.FileHelper;
import cn.oge.kdmweb.utils.ProUtil;

@Controller
public class ConfigController {

	@Autowired
	private AppPkgService appPkgService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private MConfigService mconfigService;

	@RequestMapping(value = "/module/config/{appid}_{mid}")
	public String module_config(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		
		AppPkg app = appPkgService.findAppById(appid);
		String app_installPath = app.getInstallPath();
		Module module = moduleService.getModule(app, mid);
		String module_installPath = module.getInstallPath();

		String config_filePath = FileHelper.getFilePath(app_installPath,
				module_installPath, "");
		MConfigs mConfigs = mconfigService.getMConfigs(config_filePath);

		model.addAttribute("appPkg", app);
		model.addAttribute("mConfigs", mConfigs);
		model.addAttribute("module", module);
		return "/module_config";
	}

	@RequestMapping(value = "/module/config_update", method = RequestMethod.POST)
	public String module_config_update(
			@ModelAttribute("mConfigs") MConfigs mConfigs,
			@RequestParam(value = "appPkg.appId", required = false) Integer appid,
			@RequestParam(value = "module.mid", required = false) Integer mid,
			HttpServletRequest request, Model model) {
		
		AppPkg app = appPkgService.findAppById(appid);
		List<Module> moduleList = moduleService.getModules(app);
		String appInstallPath = app.getInstallPath();
		String moduleInstallPath = "";
		for (Module module : moduleList) {
			if (mid.equals(module.getMid())) {
				moduleInstallPath = module.getInstallPath();
			}
		}
		String mconfigInstallPath = FileHelper.getFilePath(appInstallPath,
				moduleInstallPath, "");
		mconfigService.updateMConfigs(mConfigs, mconfigInstallPath);

		List<MConfig> mconfList = mConfigs.getCfgList();
		for (MConfig conf : mconfList) {
			String properPath = FileHelper.getFilePath(appInstallPath,
					moduleInstallPath, conf.getFilePath());
			List<MCItem> mcitemList = conf.getItemList();
			for (MCItem mcitem : mcitemList) {
				String keyName = mcitem.getKeyName();
				String keyValue = mcitem.getKeyValue();
				try {
					ProUtil.setProfileString(properPath, keyName, keyValue);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/module/config/" + appid + "_" + mid;
	}
	
	@RequestMapping(value = "/module/config/read_xml/{appid}_{mid}")
	public String read_xml(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		AppPkg app = appPkgService.findAppById(appid);
		List<Module> moduleList = moduleService.getModules(app);
		String appInstallPath = app.getInstallPath();
		String moduleInstallPath = "";
		for (Module module : moduleList) {
			if (mid.equals(module.getMid())) {
				moduleInstallPath = module.getInstallPath();
			}
		}
		String filePath = FileHelper.getFilePath(appInstallPath,
				moduleInstallPath, "");
		String xml_content = mconfigService.readFromFile(filePath);
		model.addAttribute("xml_content", xml_content);
		model.addAttribute("appid", appid);
		model.addAttribute("mid", mid);
		return "/edit_xml";
	}
	
	@RequestMapping(value = "/module/config/write_xml")
	public String write_xml(
			Model model,
			@RequestParam(value = "appid", required = false) Integer appid,
			@RequestParam(value = "mid", required = false) Integer mid,
			@RequestParam(value = "xml_content", required = false) String xml_content) {
		AppPkg app = appPkgService.findAppById(appid);
		List<Module> moduleList = moduleService.getModules(app);
		String appInstallPath = app.getInstallPath();
		String moduleInstallPath = "";
		for (Module module : moduleList) {
			if (mid.equals(module.getMid())) {
				moduleInstallPath = module.getInstallPath();
			}
		}
		String filePath = FileHelper.getFilePath(appInstallPath,
				moduleInstallPath, "");
		//把页面textarea内容写入mconfig.xml文件
		mconfigService.writeToFile(filePath, xml_content);
		//读取mconfig.xml文件转成java对象处理
		MConfigs mConfigs = mconfigService.getMConfigs(filePath);
		//更新.properties文件
		List<MConfig> mconfList = mConfigs.getCfgList();
		for (MConfig conf : mconfList) {
			String properPath = FileHelper.getFilePath(appInstallPath,
					moduleInstallPath, conf.getFilePath());
			List<MCItem> mcitemList = conf.getItemList();
			for (MCItem mcitem : mcitemList) {
				String keyName = mcitem.getKeyName();
				String keyValue = mcitem.getKeyValue();
				try {
					ProUtil.setProfileString(properPath, keyName, keyValue);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/module/config/" + appid + "_" + mid;
	}
	
}
