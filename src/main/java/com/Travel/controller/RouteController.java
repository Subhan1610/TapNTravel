package com.Travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Travel.entity.Route;

import com.Travel.service.RouteService;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;

	@PostMapping(value = "/create/routes")
	public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route) {

		Route createRoute = routeService.createRoute(route);
		return new ResponseEntity<Route>(createRoute, HttpStatus.CREATED);
	}

	@GetMapping("/routes")
	public ResponseEntity<List<Route>> retrieveAllRoute() {
		List<Route> retrieveAllRoute = routeService.retrieveAllRoute();
		return new ResponseEntity<List<Route>>(retrieveAllRoute, HttpStatus.OK);
	}

//	@GetMapping("/routes/{id}")
//	public ResponseEntity<Route> retrieveRoute(@PathVariable Stations station,@PathVariable Integer id) {
//		
//		Route retrieveRoute = routeService.retrieveRoute(station,id);
//		
//		
//		return new ResponseEntity<Route>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/routes/{id}")
//	public  ResponseEntity<HttpStatus> deleteRoute(@PathVariable Integer id) {
//		
//		 return routeService.deleteRoute(id);
//	}

	@PutMapping("/routes")
	public ResponseEntity<Route> updateRoute(@RequestBody Route route) {

		Route updateRoute = routeService.updateRoute(route);
		return new ResponseEntity<Route>(updateRoute, HttpStatus.OK);
	}
}
