package cn.oge.kdmweb.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author zuoxb
 *
 */
public class ProcessHelper {
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 获取pid列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<String> getTaskPids() throws IOException {
		Process pro = Runtime.getRuntime().exec("tasklist");
		Scanner in = new Scanner(pro.getInputStream());
		List<String> javaList = new ArrayList<String>();
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		while (in.hasNext()) {
			String line = in.nextLine();
			if (line.startsWith("java.")) {
				line = line.replaceAll(" ", "").trim();
				// 获得pid
				String temline = line.substring(0, line.indexOf("Console"));
				Matcher m = p.matcher(temline);
				String pid = m.replaceAll("").trim();
				javaList.add(pid);
			}
		}
		return javaList;

	}

	/**
	 * 传入一个key：appName绑定pid
	 * 
	 * @param key
	 * @return
	 */
	public static void savePid(String key) {
		try {
			List<String> list = getTaskPids();
			if(list.size()>0){
				String pid = list.get(list.size() - 1);
				map.put(key, pid);
				System.out.println(pid + ":=====");
			}else{
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过appName得到pid
	 * 
	 * @param key
	 * @return
	 */
	public static String getPid(String key) {
		return map.get(key);
	}

	/**
	 * kill线程，并且删除key所对应的pid
	 * 
	 * @param key
	 */
	public static void killTask(String key) {
		try {
			String pid = getPid(key);
			Runtime.getRuntime().exec("taskkill /f /pid " + pid);
			System.out.println(key + ":" + "哎呀！妈呀" + pid);
			map.remove(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		savePid("jetty");
		String value = getPid("jetty");
		System.out.println("jetty pid:" + value);
		killTask("jetty");

	}

}
