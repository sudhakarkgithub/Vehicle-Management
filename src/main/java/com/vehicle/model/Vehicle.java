package com.vehicle.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
public class Vehicle implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String vehicleName;
	private String vehicleNo;
	private String vehicleOwner;
	private String vehicleType;

	public enum VehicleType {
		
		CAR("CAR"),

		TRUCK("TRUCK"),
		
		AIRPLANE("AIRPLANE"),
		
		AMPHIBIAN("AMPHIBIAN"),
		
		BOAT("BOAT");

		private String value;

		VehicleType(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static VehicleType fromValue(String text) {
			for (VehicleType vt : VehicleType.values()) {
				if (String.valueOf(vt.value).equals(text)) {
					return vt;
				}
			}
			return null;
		}
	}
	
}
