package cn.oge.kdmweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.oge.kdmweb.domain.AppPkg;

public class TestJava {

	@Test
	public void getClassPath() {
		String classPath = TestJava.class.getResource("/").toString();
		System.out.println(classPath);
		String filePath = TestJava.class.getResource("/data-apps.xml")
				.toString();
		System.out.println(filePath);
		filePath = TestJava.class.getResource("/data-apps.xml").getPath();
		System.out.println(filePath);
	}
	
	@Test
	public void testSubString(){
		System.out.println("./path".substring(2));
	}
	
	@Test
	public void testList(){
		
		List<AppPkg> list = new ArrayList<AppPkg>();
		AppPkg pkg = new AppPkg();
		pkg.setAppName("test");
		list.add(pkg);
		pkg.setAppName("test2");
		System.out.println(list.get(0).getAppName());
		
	}
}
