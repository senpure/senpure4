package com.senpure.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * <b>不可在开始随机后加入选项 </b><br>
 * 采用的包装类，比int要稍慢，可以忽略不计
 * 
 * @author 罗中正
 * @version 1.0
 * @param <T>
 */
public class RandomWeight<T> implements Nameable {

	protected String name = "权重随机";

	private Random random = new Random();

	protected int count = 0;
	
	private boolean start = false;
	protected final List<T> items = new ArrayList<T>();
	protected int itemSize = 0;
	protected final  List<Integer> weights = new ArrayList<Integer>();

	

	@Override
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RandomWeight() {
		super();
	}

	public RandomWeight(String name) {
		super();
		this.name = name;
	}

	public RandomWeight<T> put(T item, int weight)
			 {
		if (start) {
			Assert.error("已经开始随机，不可添加");
			
		}

		count += weight;
		items.add(item);
		weights.add(weight);
		itemSize++;
		return this;

	}

	public  T random()  {

	
		start = true;
		int r = random.nextInt(count);

		int scope = 0;
		T item = null;
		for (int i = 0; i < itemSize; i++) {
			scope += weights.get(i);
			if (r < scope) {
				item = items.get(i);
				break;
			}
		}

	
		return item;
	}

	
	public int getCount() {
		return count;
	}

	
	public List<T> getItems() {
		return items;
	}

	public List<Integer> getWeights() {
		return weights;
	}


	
}
