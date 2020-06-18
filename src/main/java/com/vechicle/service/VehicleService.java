package com.vechicle.service;

import java.util.List;

import com.vehicle.model.Vehicle;

public interface VehicleService {
	Vehicle createVehicle(Vehicle vehicle);

	List<Vehicle> getVehicles();

	Vehicle viewVehicleById(String id);

	Vehicle updateVehicle(Vehicle vehicle, String id);

	boolean deleteVehicle(String id);
}
