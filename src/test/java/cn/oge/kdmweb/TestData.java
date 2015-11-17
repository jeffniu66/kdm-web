package cn.oge.kdmweb;

import java.util.ArrayList;
import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MCItem;
import cn.oge.kdmweb.domain.MConfig;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;

public class TestData {

	private static List<AppPkg> appList = new ArrayList<AppPkg>();

	public static List<AppPkg> appData() {
		if (!appList.isEmpty()) {
			return appList;
		}
		AppPkg pkg1 = new AppPkg();
		pkg1.setAppId(1);
		pkg1.setAppName("kdm-1.0");
		pkg1.setDesc("kdm 1.0 应用");
		pkg1.setInstallPath("F:\\oge\\部署工具\\kdm");
		appList.add(pkg1);
		AppPkg pkg2 = new AppPkg();
		pkg2.setAppId(2);
		pkg2.setAppName("kdm-2.0");
		pkg2.setDesc("kdm 2.0 应用");
		pkg2.setInstallPath("E:\\deploy\\kdm-integration");
		appList.add(pkg2);
		AppPkg pkg3 = new AppPkg();
		pkg3.setAppId(3);
		pkg3.setAppName("kdm-3.0");
		pkg3.setDesc("kdm 3.0 应用");
		pkg3.setInstallPath("D:\\kdm3.0");
		appList.add(pkg3);
		AppPkg pkg4 = new AppPkg();
		pkg4.setAppId(4);
		pkg4.setAppName("rmp-1.0");
		pkg4.setDesc("rmp 1.0 应用");
		pkg4.setInstallPath("D:\\rmp1.0");
		appList.add(pkg4);
		AppPkg pkg5 = new AppPkg();
		pkg5.setAppId(5);
		pkg5.setAppName("rmp-2.0");
		pkg5.setDesc("rmp 2.0 应用");
		pkg5.setInstallPath("D:\\rmp2.0");
		appList.add(pkg5);
		return appList;
	}

	private static List<Module> kdmModules = new ArrayList<Module>();
	private static List<Module> rmpModules = new ArrayList<Module>();

	public static List<Module> moduleData(String app) {

		if (app.startsWith("kdm")) {

			if (!kdmModules.isEmpty()) {
				return kdmModules;
			}

			Integer appId = 3;

			Module m1 = new Module();
			m1.setAppId(appId);
			m1.setMid(1);
			m1.setModuleName("kdm核心服务");
			m1.setInstallPath("./kdm-full-3.0.0-SNAPSHOT");
			m1.setDescs("kdm-full");
			m1.setStartScript("bin/start.bat");
			m1.setStopScript("bin/stop.bat");
			kdmModules.add(m1);

			Module m2 = new Module();
			m2.setAppId(appId);
			m2.setMid(2);
			m2.setModuleName("kdm接口服务");
			m2.setInstallPath("./kdm-rest-3.0.0-SNAPSHOT");
			m2.setDescs("kdm Rest");
			m2.setStartScript("bin/start.bat");
			m2.setStopScript("bin/stop.bat");
			kdmModules.add(m2);

			Module m3 = new Module();
			m3.setAppId(appId);
			m3.setMid(3);
			m3.setModuleName("评价量服务");
			m3.setInstallPath("./kdm-service-eva-data-distribution-3.0.0-SNAPSHOT");
			m3.setDescs("kdm-service-eva-data");
			m3.setStartScript("bin/start.bat");
			m3.setStopScript("bin/stop.bat");
			kdmModules.add(m3);

			Module m4 = new Module();
			m4.setAppId(appId);
			m4.setMid(4);
			m4.setModuleName("kdm实时数据服务");
			m4.setInstallPath("./kdm-vzdb-3.0.0-SNAPSHOT");
			m4.setDescs("kdm-vzdb");
			m4.setStartScript("bin/start.bat");
			m4.setStopScript("bin/stop.bat");
			kdmModules.add(m4);

			Module m5 = new Module();
			m5.setAppId(appId);
			m5.setMid(5);
			m5.setModuleName("kdm数据块服务");
			m5.setInstallPath("./kdm-vzdb-block-3.0.0-SNAPSHOT");
			m5.setDescs("kdm-vzdb-block");
			m5.setStartScript("bin/start.bat");
			m5.setStopScript("bin/stop.bat");
			kdmModules.add(m5);
			return kdmModules;
		}

		if (app.startsWith("rmp")) {

			if (!rmpModules.isEmpty()) {
				return rmpModules;
			}

			Integer appId = 1;

			Module m1 = new Module();
			m1.setAppId(appId);
			m1.setMid(1);
			m1.setModuleName("rmp-zookeeper");
			m1.setDescs("配置中间件");
			m1.setStartScript("bin/start.bat");
			m1.setStopScript("bin/stop.bat");
			rmpModules.add(m1);

			Module m2 = new Module();
			m2.setAppId(appId);
			m2.setMid(2);
			m2.setModuleName("rmp-activemq");
			m2.setDescs("消息中间件");
			m2.setStartScript("bin/start.bat");
			m2.setStopScript("bin/stop.bat");
			rmpModules.add(m2);

			Module m3 = new Module();
			m3.setAppId(appId);
			m3.setMid(3);
			m3.setModuleName("rmp-ddc");
			m3.setDescs("数据调度中心");
			m3.setStartScript("bin/start.bat");
			m3.setStopScript("bin/stop.bat");
			rmpModules.add(m3);

			Module m4 = new Module();
			m4.setAppId(appId);
			m4.setMid(3);
			m4.setModuleName("rmp-storm-nimbus");
			m4.setDescs("运算器nimbus模块");
			m4.setStartScript("bin/start.bat");
			m4.setStopScript("bin/stop.bat");
			rmpModules.add(m4);

			Module m5 = new Module();
			m5.setAppId(appId);
			m5.setMid(3);
			m5.setModuleName("rmp-storm-supervisor");
			m5.setDescs("运算器supervisor模块");
			m5.setStartScript("bin/start.bat");
			m5.setStopScript("bin/stop.bat");
			rmpModules.add(m5);
			return rmpModules;
		}

		return new ArrayList<Module>();
	}

	/**
	 * Kdm-integration 模块相关配置
	 */
	public static MConfigs module_config_kdm_integration() {
		
		List<MCItem> jdbcCfgList = new ArrayList<MCItem>();

		MCItem mcitem = new MCItem();
		mcitem.setKeyName("javax.persistence.jdbc.user");
		mcitem.setKeyValue("postgres");
		mcitem.setDesc("数据库用户名");
		mcitem.setDefValue("postgres");
		mcitem.setEnable(true);

		MCItem mcitem1 = new MCItem();
		mcitem1.setKeyName("javax.persistence.jdbc.password");
		mcitem1.setKeyValue("postgres");
		mcitem1.setDesc("数据库密码");
		mcitem1.setDefValue("postgres");
		mcitem1.setEnable(true);
		
		MCItem mcitem2 = new MCItem();
		mcitem2.setKeyName("javax.persistence.jdbc.url");
		mcitem2.setKeyValue("postgresql://localhost:5432/xianjiaba1207");
		mcitem2.setDesc("数据库连接信息");
		mcitem2.setDefValue("postgresql://localhost:5432/xianjiaba1207");
		mcitem2.setEnable(true);

		jdbcCfgList.add(mcitem);
		jdbcCfgList.add(mcitem1);
		jdbcCfgList.add(mcitem2);

		MConfig mconfig = new MConfig();
		mconfig.setFilePath("./conf/eclipseLink-jdbc.properties");
		mconfig.setItemList(jdbcCfgList);
		
		List<MCItem> propCfgList1 = new ArrayList<MCItem>();

		MCItem mcitem3 = new MCItem();
		mcitem3.setKeyName("local.kdm.ip");
		mcitem3.setKeyValue("127.0.0.1");
		mcitem3.setDesc("kdmIP");
		mcitem3.setDefValue("127.0.0.1");
		mcitem3.setEnable(true);

		MCItem mcitem4 = new MCItem();
		mcitem4.setKeyName("local.kdm.port");
		mcitem4.setKeyValue("20880");
		mcitem4.setDesc("kdm端口");
		mcitem4.setDefValue("20880");
		mcitem4.setEnable(true);

		propCfgList1.add(mcitem3);
		propCfgList1.add(mcitem4);

		MConfig mconfig1 = new MConfig();
		mconfig1.setFilePath("./conf/settings.properties");
		mconfig1.setItemList(propCfgList1);
		
		// TODO other file

		//====
		List<MCItem> jdbcCfgList2 = new ArrayList<MCItem>();
		MCItem mcitem6 = new MCItem();
		mcitem6.setKeyName("local.kdm.ip");
		mcitem6.setKeyValue("127.0.0.1");
		mcitem6.setDesc("ip");
		mcitem6.setDefValue("127.0.0.1");
		mcitem6.setEnable(true);
		MCItem mcitem7 = new MCItem();
		mcitem7.setKeyName("local.kdm.port");
		mcitem7.setKeyValue("20880");
		mcitem7.setDesc("端口");
		mcitem7.setDefValue("20880");
		mcitem7.setEnable(true);
		jdbcCfgList2.add(mcitem6);
		jdbcCfgList2.add(mcitem7);
		MConfig mconfig2 = new MConfig();
		mconfig2.setItemList(jdbcCfgList2);
		mconfig2.setFilePath("./conf/settings.properties");
		
		List<MConfig> cfgList = new ArrayList<MConfig>();
		cfgList.add(mconfig);
		cfgList.add(mconfig1);
		cfgList.add(mconfig2);
		
		MConfigs mconfigs = new MConfigs();
		mconfigs.setCfgList(cfgList);
		
		
		return mconfigs;
	}
	
	/**
	 * Kdm-rest 模块相关配置
	 * TODO 志东调整修改
	 */
	public static MConfigs module_config_kdm_rest() {
		
		// conf/xxx.properties
		List<MCItem> propCfgList = new ArrayList<MCItem>();

		MCItem mcitem = new MCItem();
		mcitem.setKeyName("local.kdm.ip");
		mcitem.setKeyValue("127.0.0.1");
		mcitem.setDesc("kdmIP");
		mcitem.setDefValue("127.0.0.1");
		mcitem.setEnable(true);

		MCItem mcitem1 = new MCItem();
		mcitem1.setKeyName("local.kdm.port");
		mcitem1.setKeyValue("20880");
		mcitem1.setDesc("kdm端口");
		mcitem1.setDefValue("20880");
		mcitem1.setEnable(true);

		propCfgList.add(mcitem);
		propCfgList.add(mcitem1);

		MConfig mconfig = new MConfig();
		mconfig.setFilePath("./conf/settings.properties");
		mconfig.setItemList(propCfgList);
		
		// TODO other file

		List<MConfig> cfgList = new ArrayList<MConfig>();
		cfgList.add(mconfig);
		
		MConfigs mconfigs = new MConfigs();
		mconfigs.setCfgList(cfgList);
		
		return mconfigs;
	}
	
	/**
	 * Kdm-vzdb 模块相关配置
	 * TODO 志东调整修改
	 */
	public static MConfigs module_config_kdm_vzdb() {
		
		// conf/xxx.properties
		List<MCItem> propCfgList = new ArrayList<MCItem>();

		MCItem mcitem = new MCItem();
		mcitem.setKeyName("javax.persistence.jdbc.user");
		mcitem.setKeyValue("postgres");
		mcitem.setDesc("数据库用户名");
		mcitem.setDefValue("postgres");
		mcitem.setEnable(true);

		MCItem mcitem1 = new MCItem();
		mcitem1.setKeyName("javax.persistence.jdbc.password");
		mcitem1.setKeyValue("postgres");
		mcitem1.setDesc("数据库密码");
		mcitem1.setDefValue("postgres");
		mcitem1.setEnable(true);

		propCfgList.add(mcitem);
		propCfgList.add(mcitem1);

		MConfig mconfig = new MConfig();
		mconfig.setFilePath("./conf/eclipseLink-jdbc.properties");
		mconfig.setItemList(propCfgList);
		
		// TODO other file

		List<MConfig> cfgList = new ArrayList<MConfig>();
		cfgList.add(mconfig);
		
		MConfigs mconfigs = new MConfigs();
		mconfigs.setCfgList(cfgList);
		
		return mconfigs;
	}
}
