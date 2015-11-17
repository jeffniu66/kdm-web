package cn.oge.kdmweb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.oge.kdmweb.domain.AppPkg;
import cn.oge.kdmweb.domain.MCItem;
import cn.oge.kdmweb.domain.MConfig;
import cn.oge.kdmweb.domain.MConfigs;
import cn.oge.kdmweb.domain.Module;

import com.thoughtworks.xstream.XStream;

/**
 * 参考 ：
 * <p>
 * Two Minute Tutorial<br>
 * http://xstream.codehaus.org/tutorial.html<br>
 *
 */
public class XStreamHelper {

	public static XStream getAppPkgXStream() {
		XStream xstream = new XStream();
		xstream.processAnnotations(AppPkg.class);
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("apps", List.class);
		xstream.alias("app", AppPkg.class);
		return xstream;
	}

	public static XStream getModulesXStream() {
		XStream xstream = new XStream();
		xstream.processAnnotations(Module.class);
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("modules", List.class);
		xstream.alias("module", Module.class);
		xstream.alias("mconfig", MConfig.class);
		xstream.alias("mcitem", MCItem.class);
		return xstream;
	}

	public static XStream getMConfigXStream() {
		XStream xstream = new XStream();
		xstream.processAnnotations(MConfigs.class);
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("mconfigs", MConfigs.class);
		// 隐藏List节点
		xstream.addImplicitCollection(MConfigs.class, "cfgList");
		xstream.alias("mconfig", MConfig.class);
		// filePath作为属性
		xstream.useAttributeFor(MConfig.class, "filePath");
		xstream.addImplicitCollection(MConfig.class, "itemList");
		xstream.alias("mcitem", MCItem.class);
		return xstream;
	}

	public static boolean toXmlFileWithHead(XStream xstream, Object object,
			String filePath) {

		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(filePath),
					"utf-8");
			osw.write(Constants.XML_HEADER);
			xstream.toXML(object, osw);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	public static void toXmlFile(XStream xstream, Object object, String filePath) {
		try {
			PrintWriter pw = new PrintWriter(filePath, "UTF-8");
			xstream.toXML(object, pw);
			// PrettyPrintWriter ppw=new PrettyPrintWriter(pw);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<AppPkg> xmlToAppList(String dataPath) {
		XStream xs = XStreamHelper.getAppPkgXStream();
		Object obj = xmlToObject(xs, dataPath);
		if (obj == null) {
			return new ArrayList<AppPkg>();
		}

		if (obj instanceof List) {
			return (List<AppPkg>) obj;
		}

		return new ArrayList<AppPkg>();
	}

	@SuppressWarnings("unchecked")
	public static List<Module> xmlToModuleList(String dataPath) {
		XStream xs = XStreamHelper.getModulesXStream();
		Object obj = xmlToObject(xs, dataPath);
		if (obj == null) {
			return new ArrayList<Module>();
		}

		if (obj instanceof List) {
			return (List<Module>) obj;
		}

		return new ArrayList<Module>();
	}

	public static MConfigs xmlToMconfigsList(String dataPath) {
		XStream xs = XStreamHelper.getMConfigXStream();
		Object obj = xmlToObject(xs, dataPath);
		if (obj == null) {
			return new MConfigs();
		}

		if (obj instanceof MConfigs) {
			return (MConfigs) obj;
		}

		return new MConfigs();
	}

	public static Object xmlToObject(XStream xs, String dataPath) {
		FileInputStream is = null;
		try {

			File file = new File(dataPath);
			if (!file.exists()) {
				// FIXME 欠考虑，需要调整
				file.createNewFile();
			}
			is = new FileInputStream(file);
			Object obj = xs.fromXML(is);

			is.close();
			return obj;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		MConfigs mc = xmlToMconfigsList("E:/kdm-3.0/kdm-full-3.0.0-SNAPSHOT/kdm-full-3.0.0-SNAPSHOT.xml");
		List<MConfig> aa = mc.getCfgList();
		for (MConfig bb : aa) {

			System.out.println(bb.getFilePath());

			for (MCItem gg : bb.getItemList()) {
				System.out.println(gg.getKeyName());
			}
		}

	}
}
