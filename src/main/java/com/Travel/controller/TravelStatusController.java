package com.Travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Travel.service.TravelStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class TravelStatusController {
	@Autowired
	private TravelStatusService travelStausService;

//	public abstract TravelStatus checkInTravelStatus(int tabCardId,  String inStationName);
//
//	public abstract TravelStatus checkOutTravelStatus(int travelStatusId,
//			String outStationName);

	@PostMapping(value = "checkIn/{tabCardId}/{inStationName}")
	public ResponseEntity<String> checkInTravelStatus(@Valid @PathVariable int tabCardId,
			@PathVariable String inStationName) throws JsonProcessingException {

		String checkInTravelStatus = travelStausService.checkInTravelStatus(tabCardId, inStationName);
		return new ResponseEntity<String>(checkInTravelStatus, HttpStatus.CREATED);
	}

	@PutMapping(value = "checkOut/{tapCardId}/{outStationName}")
	public ResponseEntity<String> checkOutTravelStatus(@Valid @PathVariable int tapCardId,
			@PathVariable String outStationName) throws JsonProcessingException {

		String checkOutTravelStatus = travelStausService.checkOutTravelStatus(tapCardId, outStationName);
		return new ResponseEntity<String>(checkOutTravelStatus, HttpStatus.CREATED);
	}

//	@GetMapping("/allTravelStatus")
//	public ResponseEntity<List<TravelStatus>> retrieveAllTravelStatus() {
//		
//		List<TravelStatus> retrieveAllTravelStatus = travelStausService.retrieveAllTravelStatus();
//		
//		return new ResponseEntity<List<TravelStatus>>( retrieveAllTravelStatus, HttpStatus.OK);
//		
//	}
//
//	@GetMapping("/route/{id}")
//	public Optional<TravelStatus> retrieveTravelStatus(@PathVariable Integer id) {
//		
//		Optional<TravelStatus> retrieveTravelStatus = travelStausService.retrieveTravelStatus(id);
//		
//		return retrieveTravelStatus;
//	}

//	@DeleteMapping("/deleteRoute/{id}")
//	public void deleteTravelStatus(@PathVariable Integer id) {
//		travelStausService.deleteTravelStatus(id);
//	}

}
