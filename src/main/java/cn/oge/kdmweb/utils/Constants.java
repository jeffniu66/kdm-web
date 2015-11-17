package cn.oge.kdmweb.utils;

public class Constants {
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
	public static String classPath = Constants.class.getResource("/")
			.toString();
	public static String DATA_APPS_XML = "data-apps.xml";
	public static String DATA_MODULES_XML = "modules.xml";
	public static String DATA_FILE_PATH = Constants.class.getResource(
			"/" + DATA_APPS_XML).getPath();
	public static String MODULES_FILE_PATH = Constants.class.getResource(
			"/" + DATA_MODULES_XML).getPath();
}
