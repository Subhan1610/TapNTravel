package com.Travel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.Travel.entity.Stations;

import com.Travel.exception.ValidationException;

import com.Travel.service.RouteService;
import com.Travel.service.StationService;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class StationsController {

	@Autowired
	private StationService stationService;

	@Autowired
	private RouteService routeService;

	@PostMapping(value = "/stations")
	public ResponseEntity<Stations> createStation(@Valid @RequestBody Stations stations) {

		Stations createStation = stationService.createStation(stations);
		return new ResponseEntity<Stations>(createStation, HttpStatus.CREATED);

	}

	@GetMapping("/stations")
	public ResponseEntity<List<Stations>> retrieveAllStation() {

		List<Stations> retrieveAllStation = stationService.retrieveAllStation();
		return new ResponseEntity<List<Stations>>(retrieveAllStation, HttpStatus.OK);
	}

	@GetMapping("/stations/{id}")
	public ResponseEntity<Stations> retrieveStation(@PathVariable Integer id) {
		Optional<Stations> rouitee = stationService.retrieveStation(id);

		if (rouitee.isEmpty())
			throw new ValidationException("Unable to fetch the Route id-" + id);

		return new ResponseEntity<Stations>(rouitee.get(), HttpStatus.OK);
	}

	@DeleteMapping("/stations/{id}")
	public ResponseEntity<String> deleteStation(@PathVariable Integer id) {

		return new ResponseEntity<>(stationService.deleteStation(id), HttpStatus.OK);
	}

	@PutMapping("/station")
	public ResponseEntity<Stations> updateStation(@RequestBody Stations station) {

		Stations updateStation = stationService.updateStation(station);

		return new ResponseEntity<Stations>(updateStation, HttpStatus.OK);
	}

}
