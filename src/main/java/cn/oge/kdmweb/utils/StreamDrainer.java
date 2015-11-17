package cn.oge.kdmweb.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class StreamDrainer implements Runnable {
	
	private InputStream ins;

	public StreamDrainer(InputStream ins) {
		this.ins = ins;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[] cmd = new String[] { "cmd.exe", "/c", "E:/rmp/zookeeper/bin/zkServer.cmd" };
		//String cmd="cmd.exe /k start  E:/rmp/zookeeper/bin/zkServer.cmd";
		/*String cmdBat = "E:/tomcat-6.0/bin/startup.bat"; 
		    File file =new File("E:/tomcat-6.0/bin/");*/
		//		String cmdBat = "E:/rmp/zookeeper/bin/zkServer.cmd"; 
		//	    File file =new File("E:/rmp/zookeeper/bin/");
		//String killCmd="taskkill /f /im E:/rmp/zookeeper/bin/zkServer.cmd";
		
		try {
		//	Process process = Runtime.getRuntime().exec(cmdBat,null,file);
		//	Process process = Runtime.getRuntime().exec(cmd);
			Process process = Runtime.getRuntime().exec(cmd);
			String name = ManagementFactory.getRuntimeMXBean().getName();    
			System.out.println(name);
			//process.destroy();
			
			new Thread(new StreamDrainer(process.getInputStream())).start();
			new Thread(new StreamDrainer(process.getErrorStream())).start();
			process.getOutputStream().close();
			int exitValue = process.waitFor();
			System.out.println("返回值：" + exitValue);
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
