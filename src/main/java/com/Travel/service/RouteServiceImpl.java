package com.Travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Travel.entity.Route;
import com.Travel.entity.Stations;
import com.Travel.exception.ValidationException;
import com.Travel.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Override
	public Route createRoute(Route route) {	
		
		
		return routeRepository.save(route);
	}

	@Override
	public List<Route> retrieveAllRoute() {		
		return routeRepository.findAll();
	}

	@Override
	public Route retrieveRoute(Stations station,Integer id) {		
		Optional<Route> rouitee = routeRepository.findById(id);

		if (rouitee.isEmpty())
			throw new ValidationException("Route id-" + id);

		return rouitee.get();
	}

	@Override
	public  ResponseEntity<HttpStatus> deleteRoute(Integer id) {		
		
		 try {
			 routeRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@Override
	public Route updateRoute(Route route) {
		
		Optional<Route> stationOptional = routeRepository.findById(route.getRouteId());

		if (stationOptional.isPresent())	{		

			route.setStations(stationOptional.get().getStations());
		return routeRepository.save(route);
	}
		else {
			throw new ValidationException("Route Id not found");
		}

}
}
