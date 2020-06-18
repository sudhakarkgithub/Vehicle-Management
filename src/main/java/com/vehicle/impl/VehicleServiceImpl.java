package com.vehicle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vechicle.service.VehicleService;
import com.vehicle.dao.VehicleDao;
import com.vehicle.model.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleDao vehicleDao;
	
	
	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		return vehicleDao.createVehicle(vehicle);
	}

	@Override
	public List<Vehicle> getVehicles() {
		return vehicleDao.getVehicles();
	}

	@Override
	public Vehicle viewVehicleById(String id) {
			
		return vehicleDao.viewVehicleById(id);
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle, String id) {
		
		return vehicleDao.updateVehicle(vehicle, id);
	}
	
	@Override
	public boolean deleteVehicle(String id) {
		
		return vehicleDao.deleteVehicle(id);
	}
	
}
