package cn.oge.kdmweb.domain;

/**
 * 模块配置的配置项
 * 
 * @author jimcoly
 *
 */
public class MCItem {
	/** 配置项 */
	private String keyName;
	/** 配置项描述 */
	private String desc;
	/** 配置项默认值 */
	private String defValue;
	/** 配置项值 */
	private String keyValue;
	/** 配置项是否启用 */
	private boolean enable;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
