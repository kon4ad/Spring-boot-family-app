package com.konrad.loch;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;
import com.konrad.loch.services.FamilyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(DataSourceConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // zmienic na trunkete method.
public class ServiceTest {
	
	@Autowired
	private FamilyService  familyService;
	
	@Test
	public void testFamilyCreate() throws SaveOperationException{
		assertTrue(this.familyService.createFamily() > 0);
	}
	
	@Test
	public void testSaveAndAddFatherToFamily() throws SaveOperationException{
		int famId = this.familyService.createFamily();
		
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
		int fatherId = this.familyService.addFatherTofamily(f, famId);
		assertTrue(fatherId > 0 );
		assertTrue(this.familyService.readFather(fatherId).equals(f));
	}
	
	@Test
	public void testSaveAndAddChildToFamily() throws SaveOperationException{
		int famId = this.familyService.createFamily();
		
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
		
		this.familyService.addFatherTofamily(f, famId);
		
		Child c2 = new Child();
		c2.setFirstName("Joanna");
		c2.setPESEL("14534534545");
		c2.setSecondName("WWC");
		c2.setSex("Kobieta");
		int childID = this.familyService.addChildTofamily(c2, famId);
		
		assertTrue(childID > 0);
		assertTrue(this.familyService.readChild(childID).equals(c2));
		
	}
	
	@Test
	public void testReadFamily() throws SaveOperationException{
		int famId = this.familyService.createFamily();
		
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
		
		int fatherID = this.familyService.addFatherTofamily(f, famId);
		
		Child c2 = new Child();
		c2.setFirstName("Joanna");
		c2.setPESEL("14534534545");
		c2.setSecondName("WWC");
		c2.setSex("Kobieta");
		int childID = this.familyService.addChildTofamily(c2, famId);
		
		Family createdFamily = this.familyService.readFamily(childID);
		assertTrue(createdFamily.getChild().size() == 1);
		assertTrue(createdFamily.getFather().getId() == fatherID);
	}
	
	@Test
	public void testSearchFamily() throws SaveOperationException{
		int famId = this.familyService.createFamily();
		Father f = new Father();
		f.setFirstName("Janusz");
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setPESEL("33333333333");
		f.setSecondName("Dokor");
		
		this.familyService.addFatherTofamily(f, famId);
		
		Child c2 = new Child();
		c2.setFirstName("Joanna");
		c2.setPESEL("14534534545");
		c2.setSecondName("WWC");
		c2.setSex("Kobieta");
		this.familyService.addChildTofamily(c2, famId);
		
		Child c1 = new Child();
		c1.setFirstName("Piotr");
		c1.setPESEL("12345678911");
		c1.setSecondName("Abc");
		c1.setSex("Mężczyzna");
		
		int childID1 = this.familyService.addChildTofamily(c1, famId);
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("first_name", "Piotr");
		
		List<Integer> foundChildId = this.familyService.searchChild(paramMap);
		assertTrue(foundChildId.size() == 1);
		assertTrue(foundChildId.get(0) == childID1);
	}

}
