package com.vehicle.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vechicle.service.VehicleService;
import com.vehicle.model.Vehicle;
import com.vehicle.model.VehicleValidator;

@RestController
public class VehicleController {

	@Autowired
	VehicleService vehicleService;

	@Autowired
	VehicleValidator validator;

	@RequestMapping(method = RequestMethod.POST, value = "/createvehicle")
	public ResponseEntity<?> createAccount(@RequestBody Vehicle vehicle) {
		validator.validate(vehicle);
		if (!validator.getErrors().isEmpty()) {
			return new ResponseEntity<>(validator, HttpStatus.BAD_REQUEST);
		}
		Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
		return new ResponseEntity<>(createdVehicle, HttpStatus.OK);
	}

	@RequestMapping("/getvehicles")
	public ResponseEntity<?> getVehicles() {
		List<Vehicle> vehicles = vehicleService.getVehicles();
		if (null == vehicles || vehicles.isEmpty()) {
			new ResponseEntity<>("No Vehicles are available", HttpStatus.OK);
		}
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	@RequestMapping("/vehicle/view/{id}")
	public ResponseEntity<?> viewVehicleById(@PathVariable String id) {
		validator.validateId(id);
		if (!validator.getErrors().isEmpty()) {
			return new ResponseEntity<>(validator, HttpStatus.BAD_REQUEST);
		}
		Vehicle vehicle = vehicleService.viewVehicleById(id);
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatevehicle/{id}")
	public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle, @PathParam("id") String id) {
		validator.validateId(id);
		validator.validate(vehicle);
		if (validator.getErrors().isEmpty()) {
			return new ResponseEntity<>(validator, HttpStatus.BAD_REQUEST);
		}
		 Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle, id);
		 if(null == updatedVehicle){
			 return new ResponseEntity<>("Updation of Vehicle is not Successful", HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deletevehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<>("Vehicle ID to delete should not be null or empty.", HttpStatus.BAD_REQUEST);
		}

		boolean isDeleted = vehicleService.deleteVehicle(id);
		if (isDeleted) {
			return new ResponseEntity<>("Vehicle is deleted Successfully!.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Vehicle is not deleted Successfully!.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
