package com.vehicle.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("validate")
public class VehicleValidator {
	private Set<String> errors = new HashSet<>();
	
	public void validate(Vehicle vehicle){
		if(StringUtils.isEmpty(vehicle.getVehicleName())) {
			errors.add("Provide Vehicle Name.");
		}
		
		if(StringUtils.isEmpty(vehicle.getVehicleNo())) {
			errors.add("Provide Vehicle Number.");
		}
		
		if(StringUtils.isEmpty(vehicle.getVehicleOwner())) {
			errors.add("Provide Vehicle Owner Details.");
		}
		
		if(StringUtils.isEmpty(vehicle.getVehicleType())) {
			errors.add("Provide Vehicle Type Details.");
		}
		
		if(vehicle.getVehicleType() == null || !vehicle.getVehicleType().equals(Vehicle.VehicleType.fromValue(vehicle.getVehicleType()).name())) {
			errors.add("Invalid Vehicle Type.");
		}
		
	}
	
	public void validateId(String id){
		if(StringUtils.isEmpty(id)) {
			errors.add("Vehicle Id to view cannot be empty.");
		}
	}

	public Set<String> getErrors(){
		return this.errors;
	}
}
