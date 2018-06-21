package com.senpure.base.util;

import java.util.List;


public interface Randomable<T> {
	
	public  T random();
	public List<T> getItems();
	
}
