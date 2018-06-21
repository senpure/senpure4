package com.senpure.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 读出RMB，没有正负之分
 *
 */
public class ReadRMB {
	
	private ReadRMB() {
		super();

	}

	private static final String[] UP_NUMBERS = new String[] { "零", "壹", "贰",
			"叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private static final String[] UNTIS = new String[] { "", "拾", "佰", "仟" };
	private static final String[] GROUPS = new String[] { "", "万", "亿", "兆" };

	private static final String YUAN = "元";
	private static final String INT = "整";
	private static final String JIAO = "角";
	private static final String FEN = "分";

	private static final String NUMBER_FULL = "金额过大，请人工阅读";
	private static final String NOT_NUMBER = "金额格式不正确";

	/**
	 * 支持任意格式的数字格式的金额(小数/科学计数法) 但该字符串为参数的方法不推荐使用
	 * 
	 * @param number
	 * @return
	 */
	public static String read(String number) {
		double innerNumber;
		try {
			innerNumber = Double.valueOf(number);
		} catch (NumberFormatException e) {
			return NOT_NUMBER;
		}
		return read(innerNumber);
	}

	public static String read(int number) {

		boolean negative = number < 0;
		if (negative) {
			number *= -1;

		}
		List<FourNumbers> currentGroups = new ArrayList<FourNumbers>();
		int count = 0;
		while (true) {

			int currentGroup = number % 10000;

			currentGroups.add(new FourNumbers(GROUPS[count], currentGroup));

			number /= 10000;

			if (number == 0) {

				break;
			}
			count++;
		}

		return read(currentGroups).append(YUAN).append(INT).toString();
	}

	/**
	 * 单位超过兆不能读出
	 * 
	 * @param number
	 * @return
	 */
	public static String read(long number) {
		if (number > 9999999999999999L || number < -9999999999999999L) {
			StringBuilder sb = new StringBuilder();
			sb.append(NUMBER_FULL).append("【").append(number).append("】");
			return sb.toString();
		}

		boolean negative = number < 0;
		if (negative) {
			number *= -1;

		}

		List<FourNumbers> currentGroups = new ArrayList<FourNumbers>();
		int count = 0;
		while (true) {

			int currentGroup = (int) (number % 10000);

			currentGroups.add(new FourNumbers(GROUPS[count], currentGroup));

			number /= 10000;

			if (number == 0) {

				break;
			}
			count++;
		}

		return read(currentGroups).append(YUAN).append(INT).toString();
	}

	/**
	 * 
	 * @param number
	 * @param decimazAddZero
	 *            读小数部分是否要多个零 123.45 (壹佰贰拾叁园,零,肆角伍分)
	 * @param readDecimalZero
	 *            小数部分的零是否读出来 123.04(壹佰贰拾叁园,零,零角伍分)
	 * @return
	 */
	public static String read(double number, boolean decimazAddZero,
			boolean readDecimalZero) {
		if (number > 9999999999999999L || number < -9999999999999999L) {
			StringBuilder sb = new StringBuilder();
			sb.append(NUMBER_FULL).append("【").append(number).append("】");
			return sb.toString();
		}

		BigDecimal bd = new BigDecimal(String.valueOf(number));
		boolean negative = number < 0;
		if (negative) {
			bd = bd.abs();

		}
		BigDecimal integer = new BigDecimal(String.valueOf(bd.longValue()));

		StringBuilder sb = new StringBuilder(read(integer.longValue()));
		BigDecimal decimal = bd.subtract(integer);
		decimal = decimal.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
		if (decimal.compareTo(BigDecimal.ZERO) > 0) {
			int l = sb.length();
			sb.delete(l - 1, l);
			StringBuilder sdecimal = new StringBuilder(decimal.toString()
					.substring(2));
			if (decimazAddZero) {
				sb.append(UP_NUMBERS[0]);
			}
			int jiao = Integer.valueOf(sdecimal.substring(0, 1));
			if (readDecimalZero || jiao > 0) {
				sb.append(UP_NUMBERS[jiao]).append(JIAO);
			}

			sb.append(UP_NUMBERS[Integer.valueOf(sdecimal.substring(1, 2))])
					.append(FEN);

		}

		return sb.toString();
	}

	/**
	 * 小数部分零也要读 123.045(壹佰贰拾叁园零角伍分)
	 * 
	 * @param number
	 * @return
	 */
	public static String read(double number) {

		return read(number, false, true);
	}

	private static StringBuilder read(List<FourNumbers> currentGroups) {
		StringBuilder sb = new StringBuilder();

		int cl = currentGroups.size();
		for (int i = 0; i < cl - 1; i++) {
			FourNumbers fourNumbers = currentGroups.get(i);
			if (fourNumbers.number != 0) {
				if (fourNumbers.full) {

					sb.insert(0, fourNumbers.group).insert(0, fourNumbers.read);

					if (currentGroups.get(i + 1).number == 0) {
						sb.insert(0, UP_NUMBERS[0]);
					}
				} else {
					sb.insert(0, fourNumbers.group).insert(0, fourNumbers.read)
							.insert(0, UP_NUMBERS[0]);
				}
			}

		}
		FourNumbers fourNumbers = currentGroups.get(cl - 1);
		sb.insert(0, fourNumbers.group).insert(0, fourNumbers.read);

		return sb;

	}

	private static String readFourNumbers(int number) {
		String str = String.valueOf(number);
		int numLength = str.length();
		StringBuilder sb = new StringBuilder();
		boolean lastNumberIsZero = true;
		for (int i = 0; i < numLength; i++) {
			int currentNumber = number % 10;
			if (currentNumber == 0) {
				if (!lastNumberIsZero) {
					sb.insert(0, UP_NUMBERS[0]);

					lastNumberIsZero = true;
				}
			} else {
				sb.insert(0, UNTIS[i]).insert(0, UP_NUMBERS[currentNumber]);

				lastNumberIsZero = false;
			}

			number /= 10;
		}

		return sb.toString();
	}

	/**
	 * 将数字转换成对应的大写方式
	 * 
	 * @param number
	 * @return
	 */
	public static String toUpperCase(int number) {
		number = number < 0 ? number * -1 : number;
		String str = String.valueOf(number);
		int numLength = str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numLength; i++) {
			int currentNumber = number % 10;

			sb.insert(0, UP_NUMBERS[currentNumber]);
			number /= 10;

		}

		return sb.toString();
	}

	/**
	 * 将数字转换成对应的大写方式
	 * 
	 * @param number
	 * @return
	 */
	public static String toUpperCase(long number) {
		number = number < 0 ? number * -1 : number;
		String str = String.valueOf(number);

		int numLength = str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numLength; i++) {
			int currentNumber = (int) (number % 10);

			sb.insert(0, UP_NUMBERS[currentNumber]);
			number /= 10;

		}

		return sb.toString();
	}

	/**
	 * 将数字转换成对应的大写方式(小数点不读)
	 * 
	 * @param number
	 * @return
	 */
	public static String toUpperCase(double number) {
		BigDecimal bd = new BigDecimal(String.valueOf(number));
		boolean negative = number < 0;
		if (negative) {
			bd = bd.abs();

		}
		BigDecimal integer = new BigDecimal(String.valueOf(bd.longValue()));

		StringBuilder sb = new StringBuilder(toUpperCase(integer.longValue()));
		BigDecimal decimal = bd.subtract(integer);
		decimal = decimal.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
		if (decimal.compareTo(BigDecimal.ZERO) > 0) {
			StringBuilder sdecimal = new StringBuilder(decimal.toString()
					.substring(2));
			int numLength = sdecimal.length();
			for (int i = 0; i < numLength; i++) {
				String t = sdecimal.substring(i, i + 1);
				sb.append(UP_NUMBERS[Integer.valueOf(t)]);
			}

		}

		return sb.toString();
	}

	private static class FourNumbers {

		public FourNumbers(String group, int number) {
			super();
			this.group = group;
			this.number = number;
			read = readFourNumbers(number);
			full = String.valueOf(number).length() == 4;

		}

		String read;
		String group;

		boolean full;
		int number;
	}

}
