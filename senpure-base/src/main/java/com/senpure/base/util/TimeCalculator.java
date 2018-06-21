package com.senpure.base.util;


import com.senpure.base.em.Week;

import java.util.Calendar;
import java.util.Date;


/**
 * 
 * 该类主要将时间定位(不产生新的对象 )到一天/周的开始/结束<br>
 * 取得date对象这个月有多少天<br>
 * 取得日期在本季度的开始/结束月份(一月份为0)<br>
 * 修正了Calendar类中本身get(Calendar.DAY_OF_WEEK)始终以星期日为一周的开始的bug(可能是我理解错误)<br>
 * <b>如要获取日期是一周的第几天请调用getDayOfWeek()相关方法<b><br>
 * <b>一年的第一月为0,任何没有明确设定一周开始日期的可能为星期日<b>
 * 
 *
 * 
 */
public class TimeCalculator {

	private TimeCalculator() {
		super();

	}

	/**
	 * <b>校准Calendar类中的bug,可能是我自己理解有误，暂时这么处理</b><br>
	 * 注意:java原生 的Calendar中 setFirstDayOfWeek(), 对get(Calendar.DAY_OF_WEEK)<br>
	 * 是没有影响的(始终是以 星期日为一周的开始)，本方法根据setFirstDayOfWeek()设置的值进行了校准<br>
	 * (如没有显示调用默认为星期日，一周的开始可以为七天中的任意值)<br>
	 * <b>这只是一种校准方法，本类中有大量的公开方法都 以此方法为基础
	 * 
	 */
	private static int calibrationDayOfWeek(Calendar calendar) {
		int firstDayOfWeek = calendar.getFirstDayOfWeek();
		int DAY_OF_WEEK = calendar.get(Calendar.DAY_OF_WEEK);
		int offset = 7;
		switch (firstDayOfWeek) {
		case Calendar.MONDAY:
			offset = -1;

			break;
		case Calendar.TUESDAY:

			offset = -2;
			break;
		case Calendar.WEDNESDAY:

			offset = -3;
			break;
		case Calendar.THURSDAY:

			offset = -4;
			break;
		case Calendar.FRIDAY:

			offset = -5;
			break;
		case Calendar.SATURDAY:

			offset = -6;
			break;
		case Calendar.SUNDAY:
			offset = 0;

			break;
		}
		DAY_OF_WEEK = DAY_OF_WEEK + offset;
		if (DAY_OF_WEEK <= 0) {
			DAY_OF_WEEK += 7;
		}
		return DAY_OF_WEEK;
	}

	/**
	 * 将WEEK同步到Calendar中对星期的常量定义 如 SUNDAY 同步为 1 ，MONDAY 同步为 2<br>
	 * (这是本类的内部实现和calibrationDayOfWeek()一起使用统一算法方式)
	 * 
	 * @param firstDayOfWeek
	 * @return
	 */
	private static int synchronizationWeekCalendar(Week firstDayOfWeek) {
		int numFirstDayOfWeek = firstDayOfWeek.ordinal() + 2;
		if (numFirstDayOfWeek  > 7) {
			numFirstDayOfWeek  -= 7;
		}
		return numFirstDayOfWeek;
	}

	/**
	 * 会根据calendar的setFirstDayOfWeek()值 返回相应的值
	 * 
	 * @param calendar
	 * @return
	 */
	public static int getDayOfWeek(Calendar calendar) {

		return calibrationDayOfWeek(calendar);
	}

	/**
	 * firstDayOfWeek 会覆盖calendar原有的值
	 * 
	 * @param calendar
	 * @param firstDayOfWeek
	 * @return
	 */
	public static int getDayOfWeek(Calendar calendar, Week firstDayOfWeek) {

		calendar.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		return calibrationDayOfWeek(calendar);
	}

	/**
	 * <b>很有可能星期日为一周的第一天
	 * 
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = createCalendarByDate(date);
		return calibrationDayOfWeek(calendar);
	}

	/**
	 * <b>很有可能星期日为一周的第一天
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		return calibrationDayOfWeek(calendar);
	}

	public static int getDayOfWeek(Week firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		return calibrationDayOfWeek(calendar);
	}

	public static int getDayOfWeek(Date date, Week firstDayOfWeek) {
		Calendar calendar = createCalendarByDate(date, firstDayOfWeek);
		return calibrationDayOfWeek(calendar);
	}

	private static Calendar createCalendarByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	private static Calendar createCalendarByDate(Date date, Week firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		return calendar;
	}

	public static Calendar toDayBegin(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;

	}

	public static Calendar getDayBegin(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		return toDayBegin(c);

	}

	public static Calendar getDayBegin() {
		Calendar c = Calendar.getInstance();

		return toDayBegin(c);

	}

	public static Date toDayBegin(Date date) {


		Calendar calendar = createCalendarByDate(date);
		toDayBegin(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;

	}

	public static Date getDayBegin(Date date) {

		Calendar calendar = createCalendarByDate(date);
		toDayBegin(calendar);

		return calendar.getTime();

	}

	public static Calendar toDayEnd(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}

	public static Calendar getDayEnd(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		return toDayEnd(c);
	}

	public static Calendar getDayEnd() {
		Calendar c = Calendar.getInstance();

		return toDayEnd(c);
	}

	public static Date toDayEnd(Date date) {

		Calendar calendar = createCalendarByDate(date);
		toDayEnd(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getDayEnd(Date date) {

		Calendar calendar = createCalendarByDate(date);
		toDayEnd(calendar);

		return calendar.getTime();
	}

	/**
	 * 会根据calendar的setFirstDayOfWeek()值进行计算
	 * 
	 * @param calendar
	 */
	public static Calendar toWeekBegin(Calendar calendar) {
		calendar.add(Calendar.DATE, -calibrationDayOfWeek(calendar) + 1);
		toDayBegin(calendar);
		return calendar;
	}

	public static Calendar getWeekBegin(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		c.add(Calendar.DATE, -calibrationDayOfWeek(calendar) + 1);
		toDayBegin(c);
		return c;
	}

	public static Calendar getWeekBegin() {
		Calendar calendar = Calendar.getInstance();

		return toWeekBegin(calendar);
	}

	/**
	 * firstDayOfWeek 会覆盖calendar原有的值
	 * 
	 * @param calendar
	 * @param firstDayOfWeek
	 * @return
	 */
	public static Calendar toWeekBegin(Calendar calendar, Week firstDayOfWeek) {

		calendar.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		toWeekBegin(calendar);
		return calendar;

	}

	public static Calendar getWeekBegin(Calendar calendar, Week firstDayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		c.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		toWeekBegin(c);
		return c;

	}

	public static Calendar getWeekBegin(Week firstDayOfWeek) {
		Calendar c = Calendar.getInstance();

		c.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));

		return toWeekBegin(c);

	}

	/**
	 * <b>很有可能星期日为一周的第一天
	 */
	public static Date toWeekBegin(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toWeekBegin(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getWeekBegin(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toWeekBegin(calendar);

		return calendar.getTime();
	}

	public static Date toWeekBegin(Date date, Week firstDayOfWeek) {
		Calendar calendar = createCalendarByDate(date, firstDayOfWeek);
		toWeekBegin(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getWeekBegin(Date date, Week firstDayOfWeek) {
		Calendar calendar = createCalendarByDate(date, firstDayOfWeek);
		toWeekBegin(calendar);

		return calendar.getTime();
	}

	/**
	 * 会根据calendar的setFirstDayOfWeek()值进行计算
	 * 
	 * @param calendar
	 */
	public static Calendar toWeekEnd(Calendar calendar) {
		calendar.add(Calendar.DATE, 7 - calibrationDayOfWeek(calendar));
		toDayEnd(calendar);
		return calendar;

	}

	public static Calendar getWeekEnd(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		c.add(Calendar.DATE, -calibrationDayOfWeek(calendar) + 1);
		toDayEnd(c);
		return c;

	}

	public static Calendar getWeekEnd() {
		Calendar c = Calendar.getInstance();

		return toWeekEnd(c);

	}

	/**
	 * firstDayOfWeek 会覆盖calendar原有的值
	 * 
	 * @param calendar
	 * @param firstDayOfWeek
	 * @return
	 */
	public static Calendar toWeekEnd(Calendar calendar, Week firstDayOfWeek) {
		calendar.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		toWeekEnd(calendar);
		return calendar;
	}

	public static Calendar getWeekEnd(Calendar calendar, Week firstDayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		c.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));
		toWeekEnd(c);
		return c;
	}

	public static Calendar getWeekEnd(Week firstDayOfWeek) {
		Calendar c = Calendar.getInstance();

		c.setFirstDayOfWeek(synchronizationWeekCalendar(firstDayOfWeek));

		return toWeekEnd(c);
	}

	/**
	 * <b>很有可能星期日为一周的第一天
	 */
	public static Date toWeekEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toWeekEnd(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getWeekEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toWeekEnd(calendar);

		return calendar.getTime();
	}

	public static Date toWeekEnd(Date date, Week firstDayOfWeek) {
		Calendar calendar = createCalendarByDate(date, firstDayOfWeek);
		toWeekEnd(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getWeekEnd(Date date, Week firstDayOfWeek) {
		Calendar calendar = createCalendarByDate(date, firstDayOfWeek);
		toWeekEnd(calendar);

		return calendar.getTime();
	}

	public static Calendar toQuarterBegin(Calendar calendar) {

		calendar.set(Calendar.MONTH, getFirstMonthOfCurrentQuarter(calendar));

		return toMonthBegin(calendar);
	}

	public static Calendar getQuarterBegin(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());

		return toQuarterBegin(c);
	}

	public static Calendar getQuarterBegin() {
		Calendar c = Calendar.getInstance();

		return toQuarterBegin(c);
	}

	public static Date toQuarterBegin(Date date) {
		Calendar c = createCalendarByDate(date);
		toQuarterBegin(c);
		date.setTime(c.getTimeInMillis());

		return date;
	}

	public static Date getQuarterBegin(Date date) {
		Calendar c = createCalendarByDate(date);

		return toQuarterBegin(c).getTime();
	}

	public static Calendar toQuarterEnd(Calendar calendar) {

		calendar.set(Calendar.MONTH, getLastMonthOfCurrentQuarter(calendar));
		return toMonthEnd(calendar);
	}

	public static Calendar getQuarterEnd(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		return toQuarterEnd(c);
	}

	public static Calendar getQuarterEnd() {
		Calendar c = Calendar.getInstance();

		return getQuarterEnd(c);
	}

	public static Date toQuarterEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toQuarterEnd(calendar);
		date.setTime(calendar.getTimeInMillis());
		;
		return date;
	}

	public static Date getQuarterEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);

		return toQuarterEnd(calendar).getTime();
	}

	public static Calendar toMonthBegin(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return toDayBegin(calendar);
	}

	public static Calendar getMonthBegin(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());

		return toMonthBegin(c);
	}

	public static Calendar getMonthBegin() {
		Calendar c = Calendar.getInstance();

		return toMonthBegin(c);
	}

	public static Date toMonthBegin(Date date) {
		Calendar c = createCalendarByDate(date);
		toMonthBegin(c);
		date.setTime(c.getTimeInMillis());

		return date;
	}

	public static Date getMonthBegin(Date date) {
		Calendar c = createCalendarByDate(date);

		return toMonthBegin(c).getTime();
	}

	public static Calendar toMonthEnd(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, getDaysOfCurrentMonth(calendar));

		return toDayEnd(calendar);
	}

	public static Calendar getMonthEnd(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		return toDayEnd(c);
	}

	public static Calendar getMonthEnd() {
		Calendar c = Calendar.getInstance();

		return toDayEnd(c);
	}

	public static Date toMonthEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);
		toMonthEnd(calendar);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getMonthEnd(Date date) {
		Calendar calendar = createCalendarByDate(date);

		return toMonthEnd(calendar).getTime();
	}

	/**
	 * <b>第一个月为0
	 * 
	 */
	public static int getFirstMonthOfCurrentQuarter(Calendar calendar) {
		return calendar.get(Calendar.MONTH) / 3 * 3;

	}

	/**
	 * <b>第一个月为0
	 * 
	 */
	public static int getFirstMonthOfCurrentQuarter(Date date) {
		Calendar calendar = createCalendarByDate(date);
		return calendar.get(Calendar.MONTH) / 3 * 3;

	}

	/**
	 * <b>第一个月为0
	 * 
	 */
	public static int getLastMonthOfCurrentQuarter(Calendar calendar) {
		return calendar.get(Calendar.MONTH) / 3 * 3 + 2;

	}

	/**
	 * <b>第一个月为0
	 * 
	 */
	public static int getLastMonthOfCurrentQuarter(Date date) {
		Calendar calendar = createCalendarByDate(date);
		return calendar.get(Calendar.MONTH) / 3 * 3 + 2;

	}

	/**
	 * 返回这个月有多少天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfCurrentMonth(Date date) {
		Calendar calendar = createCalendarByDate(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 返回这个月有多少天
	 * 
	 * @return
	 */
	public static int getDaysOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	public static int getDaysOfCurrentMonth(Calendar calendar) {

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 跟日期增加/减少(负数为减少)天数<br>
	 * 因为Calendar类已有相关方法所以没必要重载Calendar参数了
	 * 
	 * @param date
	 * @param day
	 */
	public static Date toAddDay(Date date, int day) {
		Calendar calendar = createCalendarByDate(date);
		calendar.add(Calendar.DATE, day);
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Date getAddDay(Date date, int day) {
		Calendar calendar = createCalendarByDate(date);
		calendar.add(Calendar.DATE, day);

		return calendar.getTime();
	}
}
