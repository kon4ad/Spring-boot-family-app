package com.konrad.loch.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
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
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, null);
			return ps;
		}, keyHolder);

		return (long) keyHolder.getKey();
	}

	@Override
	public void addFatherTofamily(Father father, int familyId) {
		Number newFatherId = this.saveFather(father);

		if (newFatherId == null) {
			return;
		}

		newFatherId = (long) newFatherId;

		String SQL_UPDATE = "update family set father_id = ? where id = ?";
		this.jdbcTemplate.update(SQL_UPDATE, newFatherId, familyId);

	}

	private Number saveFather(Father father) {

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

		return keyHolder.getKey();
	}

	@Override
	public void addChildTofamily(Child child, int familyId) {
		Number newChildId = this.saveChild(child);
		if (newChildId == null) {
			return;
		}
		newChildId = (long) newChildId;
		String SQL_UPDATE = "insert into family_childrens (family_id, childrens_id) values (?,?)";
		this.jdbcTemplate.update(SQL_UPDATE, familyId, newChildId);

	}

	private Number saveChild(Child child) {

		final String INSERT_MESSAGE_SQL = "insert into child (sex,first_name, pesel, second_name) values(?,?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, child.getSex());
			ps.setString(2, child.getFirstName());
			ps.setString(3, child.getPESEL());
			ps.setString(4, child.getSecondName());
			return ps;
		}, keyHolder);

		return keyHolder.getKey();
	}

	@Override
	public List<Integer> searchChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family readFamily(int id) {
		Family family = new Family();
		int familyFatherId = this.getFamilyFatherId(id);
		
		Father father = this.readFather(familyFatherId);
		
		List<Integer> familyChildIdList = this.searchChild(id);
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
	public List<Integer> searchChild(int familyID) {
		String SQL_QUERY = "select childrens_id from family_childrens where family_id = " + familyID;
		return this.jdbcTemplate.queryForList(SQL_QUERY, Integer.class);
	}

}
