package cn.oge.kdmweb.utils;


import java.io.File;
import java.lang.reflect.Field;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class ProccessUtil{
        static interface Kernel32 extends Library {

        public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

        public int GetProcessId(Long hProcess);
    }
    public int runExec() throws Exception {
        int exitVal = -1;
        Process proc = null;
        try {
            String osName = System.getProperty("os.name");
            String cmdBat = "E:/rmp/zookeeper/bin/zkServer.cmd"; 
            File file = new  File("E:/rmp/zookeeper/bin/");
            System.out.println("start .....");
            Runtime rt = Runtime.getRuntime();
            proc = rt.exec(cmdBat, null, file);
             System.out.println("The PID: " + getPid(proc));
            exitVal = proc.waitFor();
            System.out.println(exitVal);
        } catch (Exception runte) {
            if (proc != null) {
                proc.destroy();
            }
            System.out.println(runte.getMessage());
            throw runte;
        } finally {
            if (proc != null) {
                proc.destroy();
            }
        }
        return exitVal;
    }

    public static int getPid(Process p) {
    Field f;

    if (Platform.isWindows()) {
        try {
            f = p.getClass().getDeclaredField("handle");
            f.setAccessible(true);
            int pid = Kernel32.INSTANCE.GetProcessId((Long) f.get(p));
            return pid;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } else if (Platform.isLinux()) {
        try {
            f = p.getClass().getDeclaredField("pid");
            f.setAccessible(true);
            int pid = (Integer) f.get(p);
            return pid;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    else{}
    return 0;
}

    public static void main(String args[]) throws Exception {
        ProccessUtil c= new ProccessUtil();
        c.runExec();
    }
}