package cn.oge.kdmweb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DecZipHelper {
	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 * @param descDir
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir)
			throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replace("/",
					File.separator);
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(
					outPath.substring(0, outPath.lastIndexOf('\\')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

		}
		zip.close();
		System.out.println("******************解压完毕********************");
	}

	/**
	 * 判断zip文件内是否有某个文件夹
	 * @param zipPath zip物理路径
	 * @param fileName 文件夹名
	 * @return
	 */
	public static boolean zipFileExist(String zipPath, String fileName) {
		boolean result = false;
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(zipPath);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				String temp = zipEntry.getName();
				if (temp.endsWith("/")) {
					String str = temp.substring(0,temp.lastIndexOf("/"));
					if (fileName.equals(str)) {
						result = true;
					}
				}
			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] rags) throws IOException {
		System.out.println(zipFileExist(
				"D:\\kdm-web\\kdm-5.0\\kdm-full-3.0.0-SNAPSHOT.zip","kdm-full-3.0.0-SNAPSHOT"));

		/*
		 * String zipPath = "E:\\webTest\\kdm-integration-3.0.0-SNAPSHOT.zip";
		 * String descDir = "E:/webTest/"; unZipFiles(new File(zipPath),
		 * descDir);
		 */
		/*
		 * String str = "E:/deploy/kdm-3.0/abc/dec/de/va/";
		 * System.out.println(str.replace("/", File.separator));
		 */

	}

}
