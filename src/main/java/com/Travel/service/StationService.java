package com.Travel.service;

import java.util.List;
import java.util.Optional;

import com.Travel.entity.Stations;

public interface StationService {

	public Stations createStation(Stations stations);

	public List<Stations> retrieveAllStation();

	public Optional<Stations> retrieveStation(Integer id);

	public String deleteStation(Integer id);

	public Stations updateStation(Stations station);

}
