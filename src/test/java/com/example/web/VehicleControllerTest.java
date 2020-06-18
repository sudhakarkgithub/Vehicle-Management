package com.example.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vehicle.VehicleManagementApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VehicleManagementApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void verifySaveVehicle() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/createvehicle")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"vehicleName\" : \"Toyoto\", \"vehicleNo\" : \"12345\", \"vehicleOwner\" : \"Madhu\", \"vehicleType\":\"CAR\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.vehicleName").exists())
		.andExpect(jsonPath("$.vehicleNo").exists())
		.andExpect(jsonPath("$.vehicleOwner").value("Madhu"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateVehicle() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/updatevehicle/5")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"vehicleName\" : \"Toyoto\", \"vehicleNo\" : \"2323\", \"vehicleOwner\" : \"Madhu\", \"vehicleType\":\"CAR\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.vehicleName").exists())
		.andExpect(jsonPath("$.vehicleNo").exists())
		.andExpect(jsonPath("$.vehicleOwner").value("Madhu"))
		.andDo(print());
	}
	

	@Test
	public void allVechiles() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getvehicles").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1))).andDo(print());
	}
	
	@Test
	public void verifyVechileById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/view/5").accept(MediaType.APPLICATION_JSON))
		.andDo(print());
	}
	
	
	@Test
	public void verifyDeleteVehicle() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletevehicle/3").accept(MediaType.APPLICATION_JSON)).andDo(print());
	}
	
		
	
	

}
