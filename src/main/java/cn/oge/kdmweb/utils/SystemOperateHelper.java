package cn.oge.kdmweb.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SystemOperateHelper {
	private static List<String> list = new ArrayList<String>();
	private static Queue<String> queue = new LinkedList<String>();

	public static boolean startModule(String cmd, File file) {
		boolean result = false;
		try {
			Process pro = Runtime.getRuntime().exec(cmd, null, file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));
			// 一个模块绑定一个pid
			// ProcessHelper.savePid(moduleName);
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				list.add(line);
				queue.add(line);
			}
			int exitVal = pro.waitFor();
			if (exitVal != 0) {
				result = true;
			}
			System.out.println("Exited with error code " + exitVal);
			pro.destroy();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean stopModule(String cmd, File file) {
		boolean result = false;
		try {
			Process pro = Runtime.getRuntime().exec(cmd, null, file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			int exitVal;
			exitVal = pro.waitFor();
			if (exitVal == 0) {
				result = true;
			}
			System.out.println("Exited with error code " + exitVal);
			pro.destroy();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 日志信息放入list中方便查看 
	 * @return
	 */
	public static List<String> getMessage() {
		List<String> nlist = new ArrayList<String>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				nlist.add(list.get(i));
			}
			// nlist.addAll(list); 简洁方法
			list.clear();
		}

		return nlist;
	}
	
	/**
	 * 把启动信息放到队列里
	 * @return
	 */
	public static String getstartMessage() {
		return queue.size() > 0 ? queue.poll() : null;
	}

	public static void main(String[] args) {

		/*
		 * String cmdBat = "E:/tomcat-6.0/bin/startup.bat"; File file =new
		 * File("E:/tomcat-6.0/bin/");
		 */
		/*
		 * String cmdBat = "E:/rmp/zookeeper/bin/zkServer.cmd"; File file = new
		 * File("E:/rmp/zookeeper/bin/");
		 */

		String cmdBat = "E:\\deploy\\kdm-3.0\\kdm-full-3.0.0-SNAPSHOT\\bin\\stop.bat";
		File file = new File(
				"E:\\deploy\\kdm-3.0\\kdm-full-3.0.0-SNAPSHOT\\bin");
		SystemOperateHelper.stopModule(cmdBat, file);
		// String[] str={"a","b","c","d","e","f","g"};
		// List<String> list=Arrays.asList(str);
		//
		// System.out.println(list.get(list.size()-1));

	}

}
