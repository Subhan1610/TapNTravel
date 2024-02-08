package com.Travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Travel.entity.Route;
import com.Travel.entity.Stations;

public interface StationsRepository extends JpaRepository<Stations, Integer>{
	
	public abstract List<Stations> findAllByStationName(String stationName); 

	public abstract Stations findByStationNameAndRoute(String stationName,Route route);

}
