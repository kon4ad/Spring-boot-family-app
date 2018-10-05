package com.konrad.loch;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.konrad.loch.utils.AppUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(DataSourceConfig.class)
public class AppUtilsTest {
	
	@Autowired
	private AppUtils appUtils;

	@Test
	public void sqlQueryFromMapCreatorShouldReturnValidValue() {
		Map<String,String> myMap = new HashMap<>();
		myMap.put("first_name", "Franek");
		myMap.put("second_name", "Henio");
		assertEquals("second_name = 'Henio' and first_name = 'Franek'", this.appUtils.createQueryFromMap(myMap));
	}

}
