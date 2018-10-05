package com.konrad.loch.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;
import com.konrad.loch.utils.AppUtils;

@Repository
@Transactional
public class FamilyRepositoryImpl implements FamilyRepository {

	private JdbcTemplate jdbcTemplate;
	private AppUtils appUtils;

	@Autowired
	public FamilyRepositoryImpl(JdbcTemplate jdbcTemplate, AppUtils appUtils) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUtils = appUtils;
	}

	@Override
	public int saveNewFamily() throws SaveOperationException {

		final String INSERT_MESSAGE_SQL = "insert into family (father_id) values(?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, null);
			return ps;
		}, keyHolder);

		if(keyHolder.getKey() != null){
			return keyHolder.getKey().intValue();
		}else {
			throw new SaveOperationException();
		}
	}

	@Override
	public boolean updateFatherTofamily(int fatherId, int familyId) {
		String SQL_UPDATE = "update family set father_id = ? where id = ?";
		int rowaff = this.jdbcTemplate.update(SQL_UPDATE, fatherId, familyId);
		if(rowaff > 0){
			return true;
		}else {
			return false;
		}

	}

	public int saveFather(Father father) throws SaveOperationException {

		final String INSERT_MESSAGE_SQL = "insert into father (birth_date, first_name, pesel, second_name) values(?,?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, father.getBirthDate());
			ps.setString(2, father.getFirstName());
			ps.setString(3, father.getPESEL());
			ps.setString(4, father.getSecondName());
			return ps;
		}, keyHolder);
		
		if(keyHolder.getKey() != null){
			return keyHolder.getKey().intValue();
		}else {
			throw new SaveOperationException();
		}
		
	}

	@Override
	public boolean updateChildTofamily(int newChildId, int familyId) {
		String SQL_UPDATE = "insert into family_childrens (family_id, childrens_id) values (?,?)";
		int rowaff = this.jdbcTemplate.update(SQL_UPDATE, familyId, newChildId);
		if(rowaff > 0){
			return true;
		}else {
			return false;
		}

	}

	public int saveChild(Child child) throws SaveOperationException  {

		final String INSERT_STMT_SQL = "insert into child (sex,first_name, pesel, second_name) values(?,?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_STMT_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, child.getSex());
			ps.setString(2, child.getFirstName());
			ps.setString(3, child.getPESEL());
			ps.setString(4, child.getSecondName());
			return ps;
		}, keyHolder);

		if(keyHolder.getKey() != null){
			return keyHolder.getKey().intValue();
		}else {
			throw new SaveOperationException();
		}
	}


	@Override
	public Family readFamilyById(int id) {
		Family family = new Family();
		int familyFatherId = this.getFamilyFatherId(id);
		
		Father father = this.readFather(familyFatherId);
		
		List<Integer> familyChildIdList = this.searchChildByFamilyId(id);
		List<Child> familyChildsList = new ArrayList<>();
		for(int childId : familyChildIdList){
			familyChildsList.add(this.readChild(childId));
		}
		
		family.setFather(father);
		family.setChild(familyChildsList);
		family.setId(id);
	
		return family;
	}
	
	private int getFamilyFatherId(int familyId){
		String SQL_STMNT = "select * from family where id = " + familyId;
		return this.jdbcTemplate.query(SQL_STMNT, (rs) -> {
			if (rs.next()) {
				return rs.getInt("father_id");
			} else {
				return 0;
			}
		});
	}

	@Override
	public Child readChild(int id) {
		String SQL_STMNT = "select * from child where id = " + id;
		return this.jdbcTemplate.query(SQL_STMNT, (rs) -> {
			if (rs.next()) {
				Child f = new Child();
				f.setSex(rs.getString("sex"));
				f.setFirstName(rs.getString("first_name"));
				f.setPESEL(rs.getString("pesel"));
				f.setSecondName(rs.getString("second_name"));
				f.setId(rs.getInt("id"));
				return f;
			} else {
				return null;
			}
		});
	}

	@Override
	public Father readFather(int id) {
		String SQL_STMNT = "select * from father where id = " + id;
		return this.jdbcTemplate.query(SQL_STMNT, (rs) -> {
			if (rs.next()) {
				Father f = new Father();
				f.setBirthDate(rs.getDate("birth_date"));
				f.setFirstName(rs.getString("first_name"));
				f.setPESEL(rs.getString("pesel"));
				f.setSecondName(rs.getString("second_name"));
				f.setId(rs.getInt("id"));
				return f;
			} else {
				return null;
			}
		});
	}

	@Override
	public List<Integer> searchChildByFamilyId(int familyID) {
		String SQL_QUERY = "select childrens_id from family_childrens where family_id = " + familyID;
		return this.jdbcTemplate.queryForList(SQL_QUERY, Integer.class);
	}

	@Override
	public List<Integer> searchChildByMapParams(Map<String, String> paramMap) {
		String SQL_QUERY = "select id from child where ";
		StringBuilder sb = new StringBuilder();
		sb.append(SQL_QUERY);
		sb.append(this.appUtils.createQueryFromMap(paramMap));
		return this.jdbcTemplate.queryForList(sb.toString(), Integer.class);
	}
	
	/*private int getFamilyIdByChildId(int id){
		String SQL_QUERY = "select family_id from family_childrens where childrens_id = ?";
		return this.jdbcTemplate.queryForObject(SQL_QUERY, Integer.class, id);
	} */
	
	/*private Set<Integer> getFamilyIdsByChildrensIds(List<Integer> childIdList){
		Set<Integer> idSet = new HashSet<>();
		for(int id : childIdList){
			idSet.add(this.getFamilyIdByChildId(id));
		}
		return idSet;
	} */

	/*@Override
	public Set<Family> readFamily(List<Integer> childIdList) {
		Set<Family> familySet = new HashSet<>();
		Set<Integer> familysId = this.getFamilyIdsByChildrensIds(childIdList);
		for(int famId : familysId){
			familySet.add(this.readFamily(famId));
		}
		return familySet;
	} */

	@Override
	public int readFamilyIdByChildId(int childId) {
		String SQL_QUERY = "select family_id from family_childrens where childrens_id = ?";
		Integer retVal = this.jdbcTemplate.queryForObject(SQL_QUERY, Integer.class,childId);
		if(retVal != null){
			return retVal.intValue();
		}else{
			return -1;
		}
	}

	@Override
	public int readFatherIdByFamilyId(int familyId) {
		String SQL_QUERY = "select father_id from family where id = ?";
		Integer retVal = this.jdbcTemplate.queryForObject(SQL_QUERY, Integer.class,familyId);
		if(retVal != null){
			return retVal.intValue();
		}else{
			return -1;
		}
	}


}
