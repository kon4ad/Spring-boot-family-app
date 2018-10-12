package com.konrad.loch;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(DataSourceConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) 
@TestPropertySource(locations="classpath:test.properties")
public class ControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSimpleController() throws Exception{
		this.mockMvc.perform(get("/create/family")).andDo(print()).andExpect(status().isCreated())
		.andExpect(content().string(containsString("2")));
	}
	
	@Test
	public void testAddingChildToFamily() throws Exception{
		Child c = new Child();
		c.setFirstName("Test1");
		c.setPESEL("12345678901");
		c.setSecondName("Test2");
		c.setSex("Mężczyzna");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(c);
		System.out.println(json);
		this.mockMvc.perform(post("/add/child").contentType("application/json").param("familyid", "1").content(json)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("4")));
	}
	
	@Test
	public void testAddingFatherToFamily() throws Exception{
		Father f = new Father();
		f.setBirthDate(new Date(new GregorianCalendar(1993, 9, 3).getTimeInMillis()));
		f.setFirstName("Test1");
		f.setPESEL("12345678901");
		f.setSecondName("Test2");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(f);
		System.out.println(json);
		this.mockMvc.perform(post("/add/father").contentType("application/json").param("familyid", "1").content(json)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("2")));
	}
	
	@Test
	public void testReadFamilyByChildId() throws Exception {	
		this.mockMvc.perform(get("/read/family").param("childId", "1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json("{\"id\":1,\"father\":{\"id\":1,\"firstName\":\"Test10\",\"secondName\":\"Test11\",\"birthDate\":\"1992-04-12\",\"pesel\":\"92041223445\"},\"child\":[{\"id\":1,\"firstName\":\"Test1\",\"secondName\":\"Test5\",\"sex\":\"Mężczyzna\",\"pesel\":\"34534534545\"},{\"id\":2,\"firstName\":\"Test2\",\"secondName\":\"Test6\",\"sex\":\"Kobieta\",\"pesel\":\"34534565445\"},{\"id\":3,\"firstName\":\"Test3\",\"secondName\":\"Test7\",\"sex\":\"Mężczyzna\",\"pesel\":\"14010312312\"}]}"));
	}
	
	
	@Test
	public void testReadFamilyByFamilyId() throws Exception {	
		this.mockMvc.perform(get("/read/family/1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json("{\"id\":1,\"father\":{\"id\":1,\"firstName\":\"Test10\",\"secondName\":\"Test11\",\"birthDate\":\"1992-04-12\",\"pesel\":\"92041223445\"},\"child\":[{\"id\":1,\"firstName\":\"Test1\",\"secondName\":\"Test5\",\"sex\":\"Mężczyzna\",\"pesel\":\"34534534545\"},{\"id\":2,\"firstName\":\"Test2\",\"secondName\":\"Test6\",\"sex\":\"Kobieta\",\"pesel\":\"34534565445\"},{\"id\":3,\"firstName\":\"Test3\",\"secondName\":\"Test7\",\"sex\":\"Mężczyzna\",\"pesel\":\"14010312312\"}]}"));
	}
	
	@Test
	public void testSearchChild() throws Exception {
		this.mockMvc.perform(get("/search/child").param("first_name", "Test1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("[1]")));
	}
	
	@Test
	public void testReadFather() throws Exception {
		this.mockMvc.perform(get("/read/father/1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json("{\"id\":1,\"firstName\":\"Test10\",\"secondName\":\"Test11\",\"birthDate\":\"1992-04-12\",\"pesel\":\"92041223445\"}"));
	}
	
	@Test
	public void testReadChild() throws Exception {
		this.mockMvc.perform(get("/read/child/1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json("{\"id\":1,\"firstName\":\"Test1\",\"secondName\":\"Test5\",\"sex\":\"Mężczyzna\",\"pesel\":\"34534534545\"}"));
	}
	

}
