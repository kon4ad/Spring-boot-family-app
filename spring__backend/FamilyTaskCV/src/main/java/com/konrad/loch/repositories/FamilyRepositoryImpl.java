package com.konrad.loch.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;

@Repository
@Transactional
public class FamilyRepositoryImpl implements FamilyRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public FamilyRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public long createFamily() {
		
		final String INSERT_MESSAGE_SQL = "insert into family (father_id) values(?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();	
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
	          ps.setString(1, null);
	          return ps;
	        }, keyHolder);
	 
	        return (long) keyHolder.getKey();
	}

	@Override
	public void addFatherTofamily(Father father, int familyId) {
		Number newFatherId = this.saveFather(father);
		
		if(newFatherId == null){
			return;
		}
		
		newFatherId = (long) newFatherId;
		
		String SQL_UPDATE = "update family set father_id = ? where id = ?";
	    this.jdbcTemplate.update(SQL_UPDATE, newFatherId, familyId);
	    
		
		
	}
	
	private Number saveFather(Father father){
		
		final String INSERT_MESSAGE_SQL = "insert into father (birth_date, first_name, pesel, second_name) values(?,?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();	
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
	          ps.setDate(1, father.getBirthDate());
	          ps.setString(2, father.getFirstName());
	          ps.setString(3, father.getPESEL());
	          ps.setString(4, father.getSecondName());
	          return ps;
	        }, keyHolder);
	    
	    return  keyHolder.getKey();
	}

	@Override
	public void addChildTofamily(Child child, int familyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> searchChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family readFamily(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Child readChild(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Father readFather(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
