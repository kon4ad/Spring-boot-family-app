package com.konrad.loch.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	public int createFamily() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addFatherTofamily(Father father, int familyId) {
		// TODO Auto-generated method stub
		
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
