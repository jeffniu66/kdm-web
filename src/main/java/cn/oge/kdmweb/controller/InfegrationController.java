package cn.oge.kdmweb.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.oge.kdmweb.utils.FileHelper;

@Controller
public class InfegrationController {
	@RequestMapping("apps/infegration")
	public String showInfegration(
			Model model,
			@RequestParam(value = "file", required = false, defaultValue = "E:\\deploy\\kdm-integration") File file) {
		List<String> list = FileHelper.showFiles(file);
		model.addAttribute("fileList", list);
		return "/kdm-infegration";

	}

}
