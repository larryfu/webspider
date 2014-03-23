package cn.larry.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private  static final String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat(TIME_FORMAT); 
		String time = sdf.format(new Date());
		return time;
	}
}
