package cn.oge.kdmweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.oge.kdmweb.domain.AppPkg;

import com.thoughtworks.xstream.XStream;

public class TestXStream {
	
	@Test
	public void toXmlFromObject() {
		AppPkg pkg1 = new AppPkg();
		pkg1.setAppId(1);
		pkg1.setAppName("kdm-1.0");
		pkg1.setDesc("kdm 1.0 应用");
		pkg1.setInstallPath("F:\\oge\\部署工具\\kdm");
		
		XStream xstream = new XStream();
		xstream.alias("app", AppPkg.class);
		String xmlContent = xstream.toXML(pkg1);
		System.out.println(xmlContent);
	}
	
	@Test
	public void toXmlFromList() {
		List<AppPkg> applist = new ArrayList<AppPkg>();
		AppPkg pkg1 = new AppPkg();
		pkg1.setAppId(1);
		pkg1.setAppName("kdm-1.0");
		pkg1.setDesc("kdm 1.0 应用");
		pkg1.setInstallPath("F:\\oge\\部署工具\\kdm");
		applist.add(pkg1);
		AppPkg pkg2 = new AppPkg();
		pkg2.setAppId(2);
		pkg2.setAppName("kdm-2.0");
		pkg2.setDesc("kdm 2.0 应用");
		pkg2.setInstallPath("E:\\deploy\\kdm-integration");
		applist.add(pkg2);
		XStream xstream = new XStream();
		xstream.alias("apps", List.class);
		xstream.alias("app", AppPkg.class);
		String xmlContent = xstream.toXML(applist);
		System.out.println(xmlContent);
	}
	
	
}
