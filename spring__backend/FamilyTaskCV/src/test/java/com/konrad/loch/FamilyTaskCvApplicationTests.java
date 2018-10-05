package com.konrad.loch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.konrad.loch.repositories.FamilyRepository;
import com.konrad.loch.repositories.FamilyRepositoryImpl;
import com.konrad.loch.utils.AppUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(DataSourceConfig.class)
public class FamilyTaskCvApplicationTests {
	

	@Test
	public void contextLoads() {
	}

}
