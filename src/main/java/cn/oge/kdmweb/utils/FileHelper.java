package cn.oge.kdmweb.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	public static void showFile(File file) {
		File[] fs = file.listFiles();
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].getAbsolutePath());
			if (fs[i].isDirectory()) {
				showFile(fs[i]);
			}
		}
	}

	public static List<String> showFiles(File file) {
		File[] fs = file.listFiles();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {
			String str = fs[i].getAbsolutePath();
			String strs = str.substring(str.lastIndexOf("\\") + 1);
			list.add(strs);
			if (fs[i].isDirectory()) {
				showFiles(fs[i]);
			}
		}
		return list;
	}

	public static List<String> listSubFiles(File file) {
		File[] fs = file.listFiles();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {
			String str = fs[i].getAbsolutePath();
			String strs = str.substring(str.lastIndexOf("\\") + 1);
			list.add(strs);
		}
		return list;
	}

	public static String getFilePath(String appInstallPath,
			String moduleInstallPath, String filePath) {
		String fileAbsolutePath = null;
		if (filePath != null && filePath.endsWith(".properties")) {
			fileAbsolutePath = (appInstallPath
					+ moduleInstallPath.substring(1).trim()
					+ filePath.substring(1).trim()).replace("/", File.separator);
		} else {
			fileAbsolutePath = (appInstallPath
					+ moduleInstallPath.substring(1).trim() + File.separator
					+ "mconfig.xml").replace("/", File.separator);
		}

		return fileAbsolutePath;

	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean fileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (file.exists()) {  
			if (file.isFile()) { // 为文件时调用删除文件方法
				flag= deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				flag= deleteDirectory(sPath);
			}
		} 
		return flag;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		/*
		 * String appInstallPath="E:/deploy/kdm-3.0"; String
		 * moduleInstallPath="./kdm-full-3.0.0-SNAPSHOT"; String
		 * xmlName="kdm-full-3.0.0-SNAPSHOT.xml"; String xmlName2=null; String
		 * pro="./conf/eclipseLink-jdbc.properties";
		 * System.out.println(getFileName
		 * (appInstallPath,moduleInstallPath,null,xmlName));
		 */
		/*
		 * System.out
		 * .println(fileExist("E:\\deploy\\kdm-3.0\\kdm-integration.zip"));
		 */

		// deleteDirectory("D:\\ab");
		// deleteFile("D:\\test  - 副本.csv");

		System.out.println(getFilePath("E:/deploy/kdm-3.0",
				"./kdm-full-3.0.0-SNAPSHOT",
				"./conf/eclipseLink-jdbc.properties"));

	}

}
