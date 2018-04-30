package com.pq.toolslibrary;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换工具类
 */
public class DateFormatUtils {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 字符串转换成Date类
	 * @param dateStr str类型的日期，格式为yyyy-MM-dd HH:mm:ss
	 * @return 返回Date类型
	 * @throws ParseException
     */
	public synchronized static Date parseDate(String dateStr) throws ParseException {
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw e;
		}
	}

	/**
	 * 字符串转换成long型时间戳
	 * @param dateStr str类型的日期，格式为yyyy/MM/dd HH:mm:ss
	 * @return
     */
	public synchronized static long getTime(String dateStr) {
		try {
			return dateFormat2.parse(dateStr).getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Date对象转换成字符串时间
	 * @param date Date类型对象
	 * @return 格式yyyy-MM-dd HH:mm:ss
     */
	public synchronized static String formatDate(Date date) {
		return dateFormat.format(date);
	}
	/**
	 * 只返回 年月日 的工具类
	 * @param date
	 * @return
	 */
	public synchronized static String formatDateWithYMH(Date date) {
		return ymdFormat.format(date);
	}

}
