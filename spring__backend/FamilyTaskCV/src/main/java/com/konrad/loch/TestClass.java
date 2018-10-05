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
		
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
	
		Child c1 = new Child();
		c1.setFirstName("Piotr");
		c1.setPESEL("12345678911");
		c1.setSecondName("Abc");
		c1.setSex("Mężczyzna");
		
		Child c2 = new Child();
		c2.setFirstName("Joanna");
		c2.setPESEL("14534534545");
		c2.setSecondName("WWC");
		c2.setSex("Kobieta");
		
		int familyId = this.familyService.createFamily();
		System.out.println(familyId);
		int id2 = this.familyService.addFatherTofamily(f, familyId);
		System.out.println(id2);
		int idc1 = this.familyService.addChildTofamily(c1, familyId);
		System.out.println(idc1);
		int idc2 = this.familyService.addChildTofamily(c2, familyId);
		System.out.println(idc2);
		Family fam = this.familyService.readFamily(1);
		Family fam2 = this.familyService.readFamily(idc2);
		//System.out.println(fam);
		//System.out.println(fam2);
	}
	
	//change to execute file.
	@PreDestroy
	public void clearschemasindb() {
		System.out.println("drop referencies");
		this.jt.execute("alter table family drop foreign key FK_family_father");
		this.jt.execute("alter table family_childrens drop foreign key FK_family_child");
		this.jt.execute("alter table family_childrens drop  foreign key FK_family_family");
	}

}
