package com.fv.shiftreport.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
	
	public static String getDisplayDate(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(Calendar.getInstance().getTime());
	}

}
