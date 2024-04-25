package com.topnetwork.wallet.common.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeU {

	public static Date getDate(Date date,int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
}
