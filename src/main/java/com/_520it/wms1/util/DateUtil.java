package com._520it.wms1.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date getEndDate(Date current) {
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	};

	public static void main(String[] args) {
		System.out.println(getEndDate(new Date()).toLocaleString());
	}
}
