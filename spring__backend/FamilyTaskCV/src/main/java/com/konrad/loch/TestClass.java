package com.konrad.loch;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.services.FamilyService;




@Component
public class TestClass implements CommandLineRunner {


	@Autowired
	private FamilyService familyService;
	
	@Autowired 
	private JdbcTemplate jt;
	@Override
	public void run(String... args) throws Exception {
	}
	
	@PreDestroy
	public void clearschemasindb() {
		this.jt.execute("alter table family drop foreign key FK_family_father");
		this.jt.execute("alter table family_childrens drop foreign key FK_family_child");
		this.jt.execute("alter table family_childrens drop  foreign key FK_family_family");
	}

}
