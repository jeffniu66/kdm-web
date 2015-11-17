package cn.oge.kdmweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.domain.ModuleFormBean;
import cn.oge.kdmweb.service.AppPkgService;
import cn.oge.kdmweb.service.ModuleService;
import cn.oge.kdmweb.utils.CopyFile;
import cn.oge.kdmweb.utils.DecZipHelper;
import cn.oge.kdmweb.utils.FileHelper;

@Controller
public class ModuleController {

	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	@Autowired
	private AppPkgService appPkgService;
	@Autowired
	private ModuleService moduleService;
	

	/**
	 * 参考：http://sueshanxiao.blog.163.com/blog/static/21659405920131533713258/
	 */
	@RequestMapping(value = { "/app/{appName:.+}" })
	public String page_module(@PathVariable String appName,
			@RequestParam(value = "m", required = false) String moduleName,
			Model model) {

		AppPkg appPkg = appPkgService.getAppPkg(appName);
		if (appPkg == null) {
			// FIXME 硬编码
			appName = "kdm-1.0";
			appPkg = appPkgService.getAppPkg(appName);
		}
		model.addAttribute("appPkg", appPkg);

		List<Module> modules = moduleService.getModules(appPkg);
		if (modules == null) {
			modules = new ArrayList<Module>();
		}
		for (Module m : modules) {
			if (map.containsKey(m.getMid())) {
				m.setStatus(map.get(m.getMid()));
			} else {
				m.setStatus(0);
			}
		}
		model.addAttribute("modules", modules);

		if (moduleName == null) {

			return "/module_list";
		}

		// =================================
		// 模块查看页面
		// =================================
		Module thisModule = modules.get(0);
		for (Module module : modules) {
			if (moduleName.equals(module.getModuleName())) {
				thisModule = module;
				break;
			}
		}
		String modulePath = thisModule.getInstallPath();
		String appPath = appPkg.getInstallPath();
		// 相对路径
		if (modulePath.startsWith("./")) {
			// 部署路径
			modulePath = appPath + File.separator + modulePath.substring(2);
		}

		model.addAttribute("moduleName", moduleName);
		model.addAttribute("installPath", appPath);
		// 部署module路径
		model.addAttribute("modulePath", modulePath);

		// 找到部署路径将文件夹遍历出来
		File file = new File(modulePath);
		// List<String> list = FileHelper.showFiles(file);
		model.addAttribute("fileList", file.listFiles());

		return "/module_view";
	}

	@ResponseBody
	@RequestMapping(value = "/module/message", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getMessage(Model model,
			@RequestParam(value = "appId", required = false) Integer appId,
			@RequestParam(value = "mid", required = false) Integer mid) {
		return moduleService.getStartLogMessage();

	}

	@ResponseBody
	@RequestMapping(value = "/module/getlogmessage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public List<String> getlogMessage(Model model,
			@RequestParam(value = "appId", required = false) Integer appId,
			@RequestParam(value = "mid", required = false) Integer mid) {
		return moduleService.getStartMessage();

	}

	@RequestMapping(value = "/modules")
	public @ResponseBody List<Module> modules() {
		String appName = "kdm-1.0";
		List<Module> modules = moduleService.getModules(appName);

		return modules;
	}

	@RequestMapping(value = "/module/add/{appId}")
	public String page_module_add(Model model, @PathVariable Integer appId) {
		ModuleFormBean mfb = new ModuleFormBean();
		mfb.setMappId(appId);
		mfb.setModule(new Module());
		model.addAttribute("mfb", mfb);
		model.addAttribute("appId", appId);
		return "/module_add";
	}

	@RequestMapping(value = "/module/edit/{appid}_{mid}")
	public String page_module_edit(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		AppPkg app = appPkgService.findAppById(appid);
		List<Module> mlist = moduleService.getModules(app);
		if (!mlist.isEmpty()) {
			for (Module module : mlist) {
				if (mid.equals(module.getMid())) {
					ModuleFormBean mfb = new ModuleFormBean();
					mfb.setMappId(appid);
					mfb.setModule(module);
					model.addAttribute("mfb", mfb);
				}
			}
		}
		return "/module_edit";
	}

	@RequestMapping(value = "/module/copy/{appid}_{mid}")
	public String page_module_copy(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		AppPkg app = appPkgService.findAppById(appid);
		List<Module> mlist = moduleService.getModules(app);
		if (!mlist.isEmpty()) {
			for (Module module : mlist) {
				if (mid.equals(module.getMid())) {
					ModuleFormBean mfb = new ModuleFormBean();
					mfb.setMappId(appid);
					mfb.setModule(module);
					model.addAttribute("mfb", mfb);
				}
			}
		}
		return "/module_copy";
	}

	@RequestMapping(value = "/module/save", method = RequestMethod.POST)
	public String moduleSave(@ModelAttribute("mfb") ModuleFormBean mfb) {
		AppPkg app = null;
		Module _module = mfb.getModule();
		// =====
		boolean result = false;
		if (mfb.getMappId() != null && mfb.getModule().getMid() == null) {
			// 新增
			Integer appId = mfb.getMappId();
			app = appPkgService.findAppById(appId);
			result = moduleService.addModule(_module, app);
		} else {
			// 编辑
			Integer appId = mfb.getMappId();
			app = appPkgService.findAppById(appId);
			result = moduleService.updateModule(_module,app);
		}
		if (result == true) {
			return "redirect:/app/" + app.getAppName();
		}
		return "/fail_view";

	}

	@RequestMapping(value = "/module/copy", method = RequestMethod.POST)
	public String moduleCopy(
			@ModelAttribute("mfb") ModuleFormBean mfb,
			@RequestParam(value = "installPath", required = false) String pre_installPath) {
		AppPkg app = null;
		boolean result = false;
		String[] str = pre_installPath.split("/");
		Module _module = mfb.getModule();

		String now_installPath = _module.getInstallPath();
		String[] str1 = now_installPath.split("/");

		Integer appId = mfb.getMappId();
		app = appPkgService.findAppById(appId);
		String app_installPath = app.getInstallPath();

		String _pre_installPath = (app_installPath + File.separator + str[1])
				.replace("/", File.separator);
		String _now_installPath = (app_installPath + File.separator + str1[1])
				.replace("/", File.separator);
		CopyFile.copyFolder(_pre_installPath, _now_installPath);

		//result = moduleService.addModule(_module, app.getAppName(),
		//		app.getInstallPath());
		result = moduleService.addModule(_module, app);
		if (result) {
			return "redirect:/app/" + app.getAppName();
		}
		return "/fail_view";

	}

	@RequestMapping(value = "/module/delete/{appid}_{mid}")
	public String action_module_delete(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		boolean result = false;
		String message = "删除数据失败！";
		AppPkg app = appPkgService.findAppById(appid);
		Module module = moduleService.getModule(app, mid);
		result = moduleService.deleteModule(module, app);

		String app_installPath = app.getInstallPath();
		String m_inatallPath = module.getInstallPath();
		String DirePath = (app_installPath + m_inatallPath.substring(
				m_inatallPath.indexOf(".") + 1).trim()).replace("/",
				File.separator);
		// 删除部署文件夹
		FileHelper.deleteDirectory(DirePath);
		// 删除压缩文件
		String filePath = DirePath + ".zip";
		FileHelper.deleteFile(filePath);
		if (result) {
			return "redirect:/app/" + app.getAppName();
		}
		model.addAttribute("message", message);
		return "/fail_view";
	}

	@RequestMapping(value = "/module/showMessage/{appid}_{mid}")
	public String show_module_ToMessage(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		AppPkg app = appPkgService.findAppById(appid);
		model.addAttribute("appId", appid);
		model.addAttribute("mid", mid);
		model.addAttribute("appName", app.getAppName());
		Module module = moduleService.getModule(app, mid);
		// status=1 说明已经启动
		map.put(module.getMid(), 1);
		model.addAttribute("moduleName", module.getModuleName());
		return "/module_start_message";
	}

	@RequestMapping(value = "/module/logMessage/{appid}_{mid}")
	public String show_log_Message(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		AppPkg app = appPkgService.findAppById(appid);
		model.addAttribute("appId", appid);
		model.addAttribute("mid", mid);
		model.addAttribute("appName", app.getAppName());
		Module module = moduleService.getModule(app, mid);
		model.addAttribute("moduleName", module.getModuleName());
		return "/show_startlog_message";
	}

	@ResponseBody
	@RequestMapping(value = "/module/start")
	public String module_start(
			@RequestParam(value = "appId", required = false) Integer appId,
			@RequestParam(value = "mid", required = false) Integer mid,
			Model model) {
		AppPkg app = appPkgService.findAppById(appId);
		Module module = moduleService.getModule(app, mid);
		moduleService.startModule_(app.getAppName(), module);
		return "start";
	}

	@RequestMapping(value = "/module/stop/{appid}_{mid}")
	public String module_stop(@PathVariable Integer appid,
			@PathVariable Integer mid, Model model) {
		String message = "所操作的文件不存在，请检查是否存在！";
		AppPkg app = appPkgService.findAppById(appid);
		Module module = moduleService.getModule(app, mid);
		boolean result = moduleService.stopModule(app.getAppName(), module);
		if (result) {
			map.put(module.getMid(), 0);
			return "redirect:/app/" + app.getAppName();
		}
		model.addAttribute("message", message);
		return "/fail_view";
	}

	@RequestMapping(value = "/module/uploadmodele/{appId}", method = RequestMethod.POST)
	public String uploadModule(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable Integer appId, HttpServletRequest request, Model model) {
		AppPkg app = appPkgService.findAppById(appId);
		// 1.上传文件
		String path = app.getInstallPath();
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 先删除旧的文件
		try {
			boolean createNewFile = targetFile.createNewFile();
			if (createNewFile) {
				System.out.println("删除成功！");
			} else {
				boolean isDelete = targetFile.delete();
				System.out.println("删除结果：" + isDelete);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2.解压文件到当前目录 目前暂时只支持解压zip
		String filePath = path + File.separator + fileName;
		// 判断文件是否存在
		boolean exist = FileHelper.fileExist(filePath);
		if (exist == true && fileName.endsWith(".zip")) {
			File zipFile = new File(filePath);
			String descDir = null;
			boolean res = DecZipHelper.zipFileExist(filePath, fileName
					.substring(0, fileName.lastIndexOf(".")).trim());
				if (res == true) {
					descDir = path + File.separator;
				} else {
					descDir = path+ File.separator+ fileName.substring(0, fileName.lastIndexOf("."))
									.trim() + File.separator;
				}
			try {
				DecZipHelper.unZipFiles(zipFile, descDir);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			// 日志输出
			System.out.println("文件不存在或者不是zip文件");
		}

		// 3.拼装 Module 并给先关属性赋值
		ModuleFormBean mfb = new ModuleFormBean();
		Module module = new Module();
		mfb.setMappId(appId);
		module.setAppId(appId);
		module.setStartScript("bin/start.bat".trim());
		module.setStopScript("bin/stop.bat".trim());
		module.setInstallPath("./"
				+ fileName.substring(0, fileName.lastIndexOf(".")).trim());
		mfb.setModule(module);
		model.addAttribute("mfb", mfb);
		return "/module_add";
	}

	@RequestMapping(value = "module/cancel/{appId}")
	public String module_list(@PathVariable Integer appId, Model model) {
		AppPkg app = appPkgService.findAppById(appId);
		return "redirect:/app/" + app.getAppName();
	}

}
