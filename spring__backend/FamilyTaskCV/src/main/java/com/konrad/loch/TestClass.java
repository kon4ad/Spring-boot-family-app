package com.konrad.loch;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Father;
import com.konrad.loch.repositories.FamilyRepository;
@Component
public class TestClass implements CommandLineRunner {

	@Autowired
	private FamilyRepository familyRepository;
	@Override
	public void run(String... args) throws Exception {
		
		int ids= (int) this.familyRepository.createFamily();
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
		c2.setPESEL("12345678911");
		c2.setSecondName("WWC");
		c2.setSex("Kobieta");
		this.familyRepository.addChildTofamily(c1, ids);
		this.familyRepository.addChildTofamily(c2, ids);
		
		System.out.println(this.familyRepository.readFather(2));
		
		for(int i : this.familyRepository.searchChild(ids)){
			System.out.println(this.familyRepository.readChild(i));
		}
		
		System.out.println(this.familyRepository.readFamily(ids));
	}

}
