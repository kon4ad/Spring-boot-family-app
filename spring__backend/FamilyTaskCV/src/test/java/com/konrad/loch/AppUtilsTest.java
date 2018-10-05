package com.konrad.loch;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.konrad.loch.utils.AppUtils;

public class AppUtilsTest {
	
	@Autowired
	private AppUtils appUtils = new AppUtils(); //TODO change it.

	@Test
	public void sqlQueryFromMapCreatorShouldReturnValidValue() {
		Map<String,String> myMap = new HashMap<>();
		myMap.put("first_name", "Franek");
		myMap.put("second_name", "Henio");
		assertEquals("second_name = 'Henio' and first_name = 'Franek'", this.appUtils.createQueryFromMap(myMap));
	}

}
