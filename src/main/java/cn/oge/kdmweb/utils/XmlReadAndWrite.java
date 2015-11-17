package cn.oge.kdmweb.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class XmlReadAndWrite {
	/**
	 * 从文件里读取数据.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readFromFile(String filePath) throws IOException {
		File file = new File(filePath);// 指定要读取的文件
		// FileReader reader = new FileReader(file);// 获取该文件的输入流
		// 获取该文件的输入流
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"));
		char[] temp = new char[1024];// 用来保存每次读取到的字符
		String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好
		int n;// 每次读取到的字符长度
		while ((n = reader.read(temp)) != -1) {
			str += new String(temp, 0, n);
		}
		reader.close();// 关闭输入流，释放连接
		return str;
	}

	/**
	 * 往文件里写入数据.
	 * 
	 * @throws IOException
	 */
	public static void writeToFile(String filePath, String content)
			throws IOException {
		File file = new File(filePath);// 要写入的文本文件
		if (!file.exists()) {// 如果文件不存在，则创建该文件
			file.createNewFile();
		}
		// FileWriter writer = new FileWriter(file);// 获取该文件的输出流
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF-8"));
		writer.write(content);// 写内容
		writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里
		writer.close();// 关闭输出流，施放资源
	}

}
