package com.senpure.base.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu  implements Serializable{
	private static final long serialVersionUID = 13475507014246203L;
	private int id;
	private int parentId;
	private String text;
	private String description;
	private String icon;
	private String uri;
	private String badge;
	private int badgeNumber;
	private int sort;
	private boolean hasLeaf;
	private List<Menu> leafs=new ArrayList<>();
	
	
	public void setHasLeaf(boolean hasLeaf )
	{
		this.hasLeaf=hasLeaf;
	}



	public String getBadge() {
		if(badgeNumber>0)
		{return String.valueOf(badgeNumber);}
		else {
			return "";
		}
		
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	

	

	public boolean isHasLeaf() {
		return hasLeaf;
	}



	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	
	public int getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public void addBadge(int num)
	{
		this.badge+=num;
	}
	public List<Menu> getLeafs() {
		return leafs;
	}
	public void setLeafs(List<Menu> leafs) {
		this.leafs = leafs;
	}

	
}
