package com.konrad.loch.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AppUtils {

	public String createQueryFromMap(Map<String, String> paramMap){
		final String concateString = " and ";
		final String equalString = " = ";
		final String markString = "'";
		StringBuilder sb = new StringBuilder();
		paramMap.forEach((k,v) -> {
			if(!k.equals("birth_date")){
				sb.append(k);
				sb.append(equalString);
				sb.append(markString);
				sb.append(v);
				sb.append(markString);
				sb.append(concateString );
			}else{
				if(!paramMap.containsKey("pesel")){
				sb.append("pesel");
				sb.append(" LIKE ");
				sb.append(markString);
				sb.append(this.fromMilisToPeselStart(Long.parseLong(v)));
				sb.append(markString);
				sb.append(concateString);
				}
			}
		});
		
		if(sb.subSequence(sb.length()-5, sb.length()).equals(concateString)){
			sb.delete(sb.length()-5, sb.length());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String fromMilisToPeselStart(long milliseconds){
		Calendar c = Calendar.getInstance(); 
		c.setTimeInMillis(milliseconds);
		String mYear = Integer.toString(c.get(Calendar.YEAR));
		String mMonth = Integer.toString(c.get(Calendar.MONTH)); 
		String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
		mYear = mYear.substring(2);
		if(mMonth.length() == 1){
			mMonth = "0"+mMonth;
		}
		if(mDay.length() == 1){
			mDay = "0"+mDay;
		}
		
		return mYear+mMonth+mDay +'%';
		
	}
}
