package com.konrad.loch.utils;

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
			sb.append(k);
			sb.append(equalString);
			sb.append(markString);
			sb.append(v);
			sb.append(markString);
			sb.append(concateString );
		});
		
		if(sb.subSequence(sb.length()-5, sb.length()).equals(concateString)){
			sb.delete(sb.length()-5, sb.length());
		}
		
		return sb.toString();
	}
}
