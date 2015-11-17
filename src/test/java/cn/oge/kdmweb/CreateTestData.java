package cn.oge.kdmweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;
import cn.oge.kdmweb.utils.XStreamHelper;

import com.thoughtworks.xstream.XStream;

public class CreateTestData {

	@Test
	public void appPkg() {

		// List<Module> kdmModules = TestData.moduleData("kdm");

		List<AppPkg> appList = new ArrayList<AppPkg>();
		AppPkg pkg1 = new AppPkg();
		pkg1.setAppId(1);
		pkg1.setAppName("kdm-1.0");
		pkg1.setDesc("kdm 1.0 应用");
		pkg1.setInstallPath("F:\\oge\\kdm\\kdm");
		// pkg1.setModules(kdmModules);
		appList.add(pkg1);
		AppPkg pkg2 = new AppPkg();
		pkg2.setAppId(2);
		pkg2.setAppName("kdm-2.0");
		pkg2.setDesc("kdm 2.0 应用");
		pkg2.setInstallPath("E:/deploy/kdm-2.0");
		// pkg2.setModules(kdmModules);
		appList.add(pkg2);
		AppPkg pkg3 = new AppPkg();
		pkg3.setAppId(3);
		pkg3.setAppName("kdm-3.0");
		pkg3.setDesc("kdm 3.0 应用");
		pkg3.setInstallPath("E:/deploy/kdm-3.0");
		// pkg3.setModules(kdmModules);
		appList.add(pkg3);

		// List<Module> rmpModules = TestData.moduleData("rmp");

		AppPkg pkg4 = new AppPkg();
		pkg4.setAppId(4);
		pkg4.setAppName("rmp-1.0");
		pkg4.setDesc("rmp 1.0 应用");
		pkg4.setInstallPath("D:/rmp1.0");
		// pkg4.setModules(rmpModules);
		appList.add(pkg4);
		AppPkg pkg5 = new AppPkg();
		pkg5.setAppId(5);
		pkg5.setAppName("rmp-2.0");
		pkg5.setDesc("rmp 2.0 应用");
		pkg5.setInstallPath("D:/rmp2.0");
		// pkg5.setModules(rmpModules);
		appList.add(pkg5);

		XStream xstream = XStreamHelper.getAppPkgXStream();

		// String xmlContent = xstream.toXML(appList);
		// System.out.println(xmlContent);

		// String filePath = SysConts.DATA_FILE_PATH;
		String filePath = "src/main/resources/data-apps.xml";
		// XStreamHelper.toXmlFile(xstream, appList, filePath);
		XStreamHelper.toXmlFileWithHead(xstream, appList, filePath);
	}

	@Test
	public void modules() {
		List<Module> kdmModules = TestData.moduleData("kdm");
		XStream xstream = XStreamHelper.getModulesXStream();
		String filePath = "src/main/resources/modules.xml";
		XStreamHelper.toXmlFileWithHead(xstream, kdmModules, filePath);
	}	
	
	@Test
	public void module_kdm_integration_config() {
		// kdm-integration
		MConfigs intergration = TestData.module_config_kdm_integration();
		XStream xstream = XStreamHelper.getMConfigXStream();
		// 修改名称为mconfig.xml放置到kdm-integration目录下
		//String filePath = "src/test/resources/kdm-integration-mconfig.xml";
		String filePath = "src/test/resources/Mconfig.xml";
		XStreamHelper.toXmlFileWithHead(xstream, intergration, filePath);
	}
	
	@Test
	public void module_kdm_rest_config() {
		// kdm-integration
		MConfigs kdmrest = TestData.module_config_kdm_rest();
		XStream xstream = XStreamHelper.getMConfigXStream();
		// 修改名称为mconfig.xml放置到kdm-integration目录下
		//String filePath = "src/test/resources/kdm-rest-mconfig.xml";
		String filePath = "src/test/resources/Mconfig.xml";
		XStreamHelper.toXmlFileWithHead(xstream, kdmrest, filePath);
	}
	
	@Test
	public void module_kdm_vzdb_config() {
		// kdm-integration
		MConfigs kdmVzdb = TestData.module_config_kdm_vzdb();
		XStream xstream = XStreamHelper.getMConfigXStream();
		// 修改名称为mconfig.xml放置到kdm-integration目录下
		String filePath = "src/test/resources/kdm-vzdb-mconfig.xml";
		XStreamHelper.toXmlFileWithHead(xstream, kdmVzdb, filePath);
	}

	@Test
	public void modules_zuo() {
		List<Module> kdmModules = new ArrayList<Module>();
		Integer appId = 1;

		Module m1 = new Module();
		m1.setAppId(appId);
		m1.setMid(1);
		m1.setModuleName("kdm-integration");
		m1.setInstallPath("./kdm-integration");
		m1.setDescs("kdm核心");
		m1.setStartScript("bin/start.bat");
		m1.setStopScript("bin/stop.bat");
		kdmModules.add(m1);

		Module m2 = new Module();
		m2.setAppId(appId);
		m2.setMid(2);
		m2.setModuleName("kdm-rest");
		m2.setInstallPath("./kdm-rest");
		m2.setDescs("kdm Rest");
		m2.setStartScript("bin/start.bat");
		m2.setStopScript("bin/stop.bat");
		kdmModules.add(m2);

		Module m3 = new Module();
		m3.setAppId(appId);
		m3.setMid(3);
		m3.setModuleName("kdm-vzdb");
		m3.setInstallPath("./kdm-vzdb");
		m3.setDescs("与库无关");
		m3.setStartScript("bin/start.bat");
		m3.setStopScript("bin/stop.bat");
		kdmModules.add(m3);
		XStream xstream = XStreamHelper.getModulesXStream();
		String filePath = "src/test/resources/modules_zuo.xml";
		XStreamHelper.toXmlFileWithHead(xstream, kdmModules, filePath);
	}

	/**
	 * 算法预试应用模块
	 */
	@Test
	public void rmp_modules() {
		List<Module> kdmModules = TestData.moduleData("rmp");
		XStream xstream = XStreamHelper.getModulesXStream();
		String filePath = "src/test/resources/modules_rmp.xml";
		XStreamHelper.toXmlFileWithHead(xstream, kdmModules, filePath);
	}

}
