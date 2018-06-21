package com.senpure.base.util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomWeightCount<T>  {

	private long countRandom = 0;
	private long time = 0;
	private Map<T, Long> countMap = new HashMap<T, Long>();

	private NumberFormat numberFormat = NumberFormat.getPercentInstance();

	private RandomWeight<T> randomWeight;

	public RandomWeightCount(RandomWeight<T> randomWeight) {
		super();
		this.randomWeight = randomWeight;
		numberFormat.setMaximumFractionDigits(2);
	}

	
	public RandomWeightCount(RandomWeight<T> randomWeight, int showDecimal) {
		this.randomWeight = randomWeight;
		numberFormat.setMaximumFractionDigits(showDecimal);

	}

	public T random() {
		long now = System.nanoTime();
		T item =randomWeight.random();
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
		if(randomWeight instanceof Nameable)
		{
			
			sb.append(	((Nameable)randomWeight).getName()).append("，");
		}
		
		else {
			sb.append(randomWeight).append("，");
		}
		

		if (countRandom == 0) {
			sb.append("尚未进行随机！");
			result = sb.toString();
			if (printToConsole) {

				System.out.println(result);
			}
			return result;
		}
		sb.append("权重和：").append(randomWeight.getCount());
		sb.append(",共随机").append(ReadNumber.read(countRandom)).append("(").append(countRandom).append(")")
				.append(time / 1000000).append("毫秒，统计结果如下：\n");

		List<T> items=randomWeight.getItems();
		int itemSize= items.size();
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
			sb.append("_权重：").append(randomWeight.getWeights().get(i));
			sb.append("】出现次数：").append(count);
			sb.append("，实际概率：").append(numberFormat.format(count * 1D / countRandom));
			sb.append("，设定概率：").append(numberFormat.format(randomWeight.getWeights().get(i).doubleValue() / randomWeight.getCount()));

		}

		result = sb.toString();
		if (printToConsole) {
			System.out.println(result);
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

		RandomWeight<Integer> r = new RandomWeight<>();

		try {
			r.put(1, 49);
			r.put(2, 15);
			r.put(3, 75);

			r.put(4, 95);
			r.put(5, 405);
			r.put(6, 361);

			for (int i = 0; i < 10000000; i++) {
				r.random();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
