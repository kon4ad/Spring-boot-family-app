package com.konrad.loch;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Father;
import com.konrad.loch.repositories.FamilyRepository;

public class TestClass implements CommandLineRunner {

	@Autowired
	private FamilyRepository familyRepository;
	@Autowired
	private DataSource ds;
	@Autowired 
	private JdbcTemplate jt;
	@Override
	public void run(String... args) throws Exception {
		
		int ids=  this.familyRepository.createFamily();
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(2000, 11, 11));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
		this.familyRepository.addFatherTofamily(f, ids);
		
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
		this.familyRepository.addChildTofamily(c1, ids);
		this.familyRepository.addChildTofamily(c2, ids);
		
		//System.out.println(this.familyRepository.readFamily(ids)); 
		//this.familyRepository.searchChild("siema",null,"eleoelle",null,"2003-23-12");
		//this.familyRepository.searchChild(null, "siema", null,null,"061212");
		Map<String, String> myMap = new HashMap<>();
		myMap.put("pesel", "14534534545");
		System.out.println(ds.toString());
		System.out.println(this.familyRepository.searchChildByMapParams(myMap));
		System.out.println(this.familyRepository.readFamily(this.familyRepository.searchChildByMapParams(myMap)).size());
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
