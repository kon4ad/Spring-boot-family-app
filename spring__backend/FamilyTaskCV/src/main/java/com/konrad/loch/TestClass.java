package com.konrad.loch;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
		
	}

}
