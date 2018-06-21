package com.senpure.base.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 采用构建数组的方式比BigDecimal速度快7倍左右 <b>概率小数点不宜过长<=9比较好</b>
 * 
 * @author 罗中正
 * @version 1.0
 */
public class RandomChanceInt<T> extends RandomChance<T> {

	protected boolean notInit = true;
	protected int[] intItems;
	protected int multiple;

	
	
	public RandomChanceInt() {
		super();
		
	}
	public RandomChanceInt(String name) {
		super(name);
		
	}
	@Override
    public T random() {
		//算出数组列表
		if (notInit) {	
			if (count.compareTo(BigDecimal.ONE) < 0) {
				// throw new Exception("概率缺少(小于1)");
				Assert.error("概率缺少(小于1)");
			}
			int length = 0;
			for (int i = 0; i < itemSize; i++) {
				String c = chances.get(i).toString();
				int index = c.indexOf(".");
				int clength = c.length();
				int temp = clength - index - 1;
				length = temp > length ? temp : length;
			}
			intItems = new int[itemSize];
			//取出最大值
			multiple = (int) Math.pow(10, length);

			for (int i = 0; i < itemSize; i++) {

				intItems[i] = (int) (chances.get(i).doubleValue() * multiple);

				if (i > 0) {
					intItems[i] += intItems[i - 1];

				}

			}
			notInit = false;
		}
		T item = null;
		int chance = random.nextInt(multiple);
		int scope = 0;
		for (int i = 0; i < itemSize; i++) {
			scope = intItems[i];
			if (chance < scope) {
				item = items.get(i);
				break;
			}
		}
		return item;
	}
	@Override
    public List<BigDecimal> getChances() {
		return chances;
	}

	@Override
    public List<T> getItems() {
		return items;
	}
}
