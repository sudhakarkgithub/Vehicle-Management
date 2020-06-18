package com.vehicle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.vehicle.dao.VehicleDao;
import com.vehicle.model.Vehicle;

@Repository
public class VehicleDaoImpl extends JdbcDaoSupport implements VehicleDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public Vehicle createVehicle(Vehicle vehicle) {

		String sql = "INSERT INTO vehicle "
				+ "(ID, VEHICLENAME, VEHICLENO, VEHICLEOWNER, VEHICLETYPE) VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql,
				new Object[] {
						String.valueOf(jdbcTemplate.queryForObject("select nextval('seq_vehicle')", Long.class) + 1),
						vehicle.getVehicleName(), vehicle.getVehicleNo(), vehicle.getVehicleOwner(),
						vehicle.getVehicleType() });
		
		return vehicle;
	}
	
	private Vehicle viewVehicleByName(String name) {
		String sql = "select * from vehicle where id='" + name + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		Vehicle vehicle = null;
		for (Map<String, Object> row : rows) {
			vehicle = new Vehicle();
			vehicle.setId((String) row.get("id"));
			vehicle.setVehicleName((String) row.get("vehicleName"));
			vehicle.setVehicleNo((String) row.get("vehicleNo"));
			vehicle.setVehicleOwner((String) row.get("vehicleOwner"));
			vehicle.setVehicleType((String) row.get("vehicleType"));
		}
		return vehicle;
	}

	@Override
	public List<Vehicle> getVehicles() {
		String sql = "SELECT * FROM vehicle order by id::bigint desc";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Vehicle> result = new ArrayList<Vehicle>();
		for (Map<String, Object> row : rows) {
			Vehicle vehicle = new Vehicle();
			vehicle.setId((String) row.get("id"));
			vehicle.setVehicleName((String) row.get("vehicleName"));
			vehicle.setVehicleNo((String) row.get("vehicleNo"));
			vehicle.setVehicleOwner((String) row.get("vehicleOwner"));
			vehicle.setVehicleType((String) row.get("vehicleType"));
			result.add(vehicle);
		}
		return result;
	}

	@Override
	public Vehicle viewVehicleById(String id) {
		String sql = "select * from vehicle where id='" + id + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		Vehicle vehicle = null;
		for (Map<String, Object> row : rows) {
			vehicle = new Vehicle();
			vehicle.setId((String) row.get("id"));
			vehicle.setVehicleName((String) row.get("vehicleName"));
			vehicle.setVehicleNo((String) row.get("vehicleNo"));
			vehicle.setVehicleOwner((String) row.get("vehicleOwner"));
			vehicle.setVehicleType((String) row.get("vehicleType"));
		}
		return vehicle;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vehicle updateVehicle(Vehicle vehicle, String id) {
		String sql = "update vehicle set vehicleName = :vehicleName, vehicleNo = :vehicleNo, vehicleType = :vehicleType, vehicleOwner = :vehicleOwner where id = :id";
		Map params = new HashMap<String, Object>();
		params.put("id", vehicle.getId());
		params.put("vehicleName", vehicle.getVehicleName());
		params.put("vehicleNo", vehicle.getVehicleNo());
		params.put("vehicleOwner", vehicle.getVehicleOwner());
		params.put("vehicleType", vehicle.getVehicleType());
		int update = namedParameterJdbcTemplate.update(sql, params);
		if(update ==1){
			Vehicle vehicleById = viewVehicleById(id);
			return vehicleById;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteVehicle(String id) {
		String delQuery = "delete from vehicle where id = :id";
		Map params = new HashMap<String, Object>();
		params.put("id", id);
		namedParameterJdbcTemplate.update(delQuery, params);
		return true;
	}
}