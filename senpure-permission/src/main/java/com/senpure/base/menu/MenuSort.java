package com.senpure.base.menu;

import java.util.Comparator;

public class MenuSort implements Comparator<Menu> {

	
	@Override
	public int compare(Menu one, Menu two) {
		if (one.getParentId() == two.getParentId()) {

			return one.getSort() < two.getSort() ? -1
					: one.getSort() > two.getSort() ? 1 : 0;
		}
		return 0;
	}

}
