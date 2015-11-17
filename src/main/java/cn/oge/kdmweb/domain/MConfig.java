package cn.oge.kdmweb.domain;

import java.util.List;

/**
 * 配置文件
 * 
 * @author jimcoly
 *
 */
public class MConfig {
	/** 关联的配置文件的 */
	private String filePath;
	/** 配置项集合 */
	private List<MCItem> itemList;
	/** 配置文件类型 */
	private String type;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<MCItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MCItem> itemList) {
		this.itemList = itemList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
