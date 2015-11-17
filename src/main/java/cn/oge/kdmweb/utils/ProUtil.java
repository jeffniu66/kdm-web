package cn.oge.kdmweb.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProUtil {

	/**
	 * 更新properties内的配置信息
	 * @param file
	 * @param key
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public static boolean setProfileString(String file, String key, String value)
			throws IOException {
		File f = new File(file);
		boolean res = false;
		if (f.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String outstr = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				if (line == "") // 如果为空
				{
					outstr += "\n";
					continue;
				}
				if (line.startsWith("#")) // 如果为注释
				{
					outstr += line + "\n";
					continue;
				}
				if (line.trim().startsWith(key)) {
					String[] keyandvalue = line.split("=");

					outstr += keyandvalue[0].toString() + "="
							+ value.toString() + "\n";

					res = true;
					continue;
				}
				outstr += line + "\n";

			}
			if (res) {

				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(outstr);

				bw.close();
				fw.close();
				br.close();
				return true;
			}
			
		}
		return false;

	}
	
	
	public static String getPropertyValue(String key,String fileName){
		Properties pro = new Properties();  
		  try {  
		   InputStream in = new FileInputStream(fileName);  //加载配置文件  
		   try {  
		    pro.load(in);  
		   } finally {  
		    in.close();  
		   }  
		   return pro.getProperty(key);  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  
		   return null;  
		  }  
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "E:/deploy/kdm-3.0/kdm-full-3.0.0-SNAPSHOT/conf/eclipseLink-jdbc.properties";
		System.out.println(getPropertyValue("javax.persistence.jdbc.user",fileName)); 
	}

	

}
