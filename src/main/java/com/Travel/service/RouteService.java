package com.Travel.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Travel.entity.Route;
import com.Travel.entity.Stations;

public interface RouteService {
	
		
	public abstract Route createRoute(Route route);	
	
	public abstract List<Route> retrieveAllRoute(); 
	
	public abstract Route retrieveRoute(Stations station,Integer id);
	
	public  ResponseEntity<HttpStatus> deleteRoute(Integer id);	
	
	public abstract Route updateRoute( Route route);

}
