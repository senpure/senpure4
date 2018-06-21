package com.senpure.base.util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomChanceCount<T>  {

	private NumberFormat numberFormat = NumberFormat.getPercentInstance();
	private long countRandom = 0;
	private long time = 0;
	private Map<T, Long> countMap = new HashMap();
private RandomChance<T> randomChance;
	
	public RandomChanceCount(RandomChance<T> randomChance) {
		super();
		this.randomChance=randomChance;
		numberFormat.setMaximumFractionDigits(2);
	}


	public RandomChanceCount(RandomChance<T> randomChance, int showDecimal) {
		
		this.randomChance=randomChance;
		numberFormat.setMaximumFractionDigits(showDecimal);
	}
	public T random() {
		long now = System.nanoTime();
		T item = randomChance.random();

		countRandom++;
		Long c = countMap.get(item);
		if (c == null) {
			countMap.put(item, 1L);
		} else {
			countMap.put(item, ++c);
		}
		time += System.nanoTime() - now;
		return item;
	}

	public String count(boolean printToConsole) {
		String result;
		StringBuilder sb = new StringBuilder();

		if(randomChance instanceof Nameable)
		{
			
			sb.append(	((Nameable)randomChance).getName()).append("，");
		}
		
		else {
			sb.append(randomChance).append("，");
		}
		
		if (countRandom == 0) {
			sb.append("尚未进行随机！");
			result = sb.toString();
			if (printToConsole) {

				System.out.println(result);
			}
			return result;
		}
		sb.append("共随机").append(ReadNumber.read(countRandom)).append("(").append(countRandom).append(")")
				.append("次,耗时：").append(time / 1000000).append("毫秒，统计结果如下：\n");

		List<T> items=randomChance.getItems();
		int itemSize=items.size();
		for (int i = 0; i < itemSize; i++) {
			T item = items.get(i);
			long count = count(item);
			if (i != 0) {
				sb.append("\n");
			}
			sb.append("选项【");
			if (item instanceof Nameable) {
				Nameable ri = (Nameable) item;
				sb.append(ri.getName());
			} else {
				sb.append(item);
			}
			sb.append("】出现次数：").append(count);
			sb.append("，实际概率：").append(numberFormat.format(count * 1D / countRandom));
			sb.append("，设定概率：").append(numberFormat.format(randomChance.getChances().get(i).doubleValue()));

		}

		result = sb.toString();
		if (printToConsole) {
			System.out.println(sb.toString());
		}

		return result;
	}

	public String count() {
		return count(false);
	}

	public long count(T item) {
		Long count = countMap.get(item);
		count = count == null ? 0 : count;
		return count;
	}

	public long getCount() {
		return countRandom;
	}

	public static void main(String[] args) {
		RandomChanceInt<Integer> r = new RandomChanceInt<>();

		r.put(12, 0.1);
		r.put(13, 0.5);
		r.put(14, 0.2);

		r.put(15, 0.2);
		
		for (int i = 0; i < 10000000; i++) {

			r.random();
		}

		

	}
}
