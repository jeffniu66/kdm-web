package cn.oge.kdmweb.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import cn.oge.kdmweb.domain.ActionInfo;
import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.service.AppPkgService;
import cn.oge.kdmweb.utils.DecZipHelper;
import cn.oge.kdmweb.utils.FileHelper;

@Controller
public class AppPkgController {
	@Autowired
	private AppPkgService appPkgService;

	@RequestMapping(value = "/apps")
	public String page_app_list(Model model) {
		List<AppPkg> appList = appPkgService.getAppPkgs();
		model.addAttribute("appList", appList);
		return "/apps_list";
	}

	@RequestMapping(value = "/apps/add")
	public String page_app_add(Model model) {
		model.addAttribute("app", new AppPkg());
		return "/apps_add";
	}

	@RequestMapping(value = "/apps/edit/{appId}")
	public String page_app_edit(@PathVariable Integer appId, Model model) {
		AppPkg app = appPkgService.findAppById(appId);
		if (app == null) {
			// TODO 或者返回找不到对应编辑的应用
			app = new AppPkg();
		}
		model.addAttribute("app", app);

		return "/apps_edit";
	}

	@RequestMapping(value = "/apps/save", method = RequestMethod.POST)
	public String action_app_save(@ModelAttribute("app") AppPkg app) {

		if (app == null) {
			// TODO 错误处理
			return "";
		}
		// TODO "null"的判断有点丑陋
		if (app.getAppName() == null || "".equals(app.getAppName())
				|| app.getInstallPath() == null
				|| "".equals(app.getInstallPath())) {
			// TODO 错误处理
			return "";
		}

		boolean result = false;
		// 新增
		if (app.getAppId() == null) {
			String path = app.getInstallPath();
			File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			result = appPkgService.addAppPkg(app);
		}
		// 更新
		else {
			result = appPkgService.updateAppPkg(app);
		}

		if (result == true) {
			return "redirect:/apps";
		}

		return "/fail_view";
	}

	@RequestMapping(value = "/apps/delete/{appId}")
	public String action_app_delete(@PathVariable Integer appId, Model model) {
		AppPkg app = appPkgService.findAppById(appId);
		ActionInfo info = new ActionInfo();
		info.setTitle("操作信息");
		boolean result = false;
		if (app == null) {
			info.setContent("删除应用【" + appId + "】失败！");
		} else {
			result = appPkgService.deleteAppPkg(app);
			//info.setContent("删除应用【" + app.getAppName() + "】成功！");
			String app_installPath = app.getInstallPath();
			String DirePath = (app_installPath).replace("/",
					File.separator);
			// 删除部署文件夹
			FileHelper.deleteDirectory(DirePath);
			// 删除压缩文件
			String filePath = DirePath + ".zip";
			FileHelper.deleteFile(filePath);
		}
		//model.addAttribute("info", info);
		if (result) {
			return "redirect:/apps";
		}
		return "/fail_view";
	}
	
	@RequestMapping(value = "/apps/uploadmodele", method = RequestMethod.POST)
	public String uploadApp(
			@RequestParam(value = "file", required = false) MultipartFile file,
			 HttpServletRequest request, Model model) {
		//AppPkg app = appPkgService.findAppById(appId);
		// 1.上传文件
		String path = "D:\\kdm-service";
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

		// 3.拼装 AppPkg 并给先关属性赋值
		AppPkg app = new AppPkg();
		app.setInstallPath(path + File.separator
				+ fileName.substring(0, fileName.lastIndexOf(".")).trim());
		app.setModuleFile("modules.xml");
		model.addAttribute("app", app);
		return "/apps_add";
	}
}
