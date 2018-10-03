package com.konrad.loch;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

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
		
	}

}
