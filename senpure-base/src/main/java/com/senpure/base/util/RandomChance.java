package com.senpure.base.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomChance<T> implements Nameable {
	protected String name = "概率随机";
	protected Random random = new Random();
	protected BigDecimal count = BigDecimal.ZERO;
	protected int itemSize = 0;
	protected final List<T> items = new ArrayList<T>();
	protected final List<BigDecimal> chances = new ArrayList<BigDecimal>();

	public RandomChance() {
		super();

	}

	public RandomChance(String name) {
		super();
		this.name = name;
	}

	@Override
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RandomChance<T> put(T item, String chance) {
		BigDecimal bigDecimal = new BigDecimal(chance);
		count = count.add(bigDecimal);
		if (count.compareTo(BigDecimal.ONE) > 0) {
			Assert.error("概率溢出(大于1)");
		}
		items.add(item);
		chances.add(bigDecimal);
		itemSize++;
		return this;
	}

	public RandomChance<T> put(T item, double chance) {

		return put(item, String.valueOf(chance));
	}

	public RandomChance<T> put(T item, float chance) {

		return put(item, String.valueOf(chance));
	}

	public  T random() {
		if (count.compareTo(BigDecimal.ONE) < 0) {
			Assert.error("概率缺少(小于1)");	
		}
		T item = null;
		double chance = random.nextDouble();
		BigDecimal scope = BigDecimal.ZERO;
		for (int i = 0; i < itemSize; i++) {
			scope = scope.add(chances.get(i));
			if (chance < scope.doubleValue()) {
				item = items.get(i);
				break;
			}
		}	
		return item;
	}

	

	public List<BigDecimal> getChances() {
		return chances;
	}

	public List<T> getItems() {
		return items;
	}

	




	

	

}
