package com.Travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.Travel.entity.Stations;
import com.Travel.exception.ValidationException;
import com.Travel.repository.StationsRepository;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationsRepository stationsRepository;

	@Override
	public Stations createStation(Stations stations) {
		if (stations.getStationName() == null) {
			throw new ValidationException("Input should not be null.");
		}
		Stations save = stationsRepository.save(stations);
		return save;
	}

	@Override
	public List<Stations> retrieveAllStation() {

		return stationsRepository.findAll();
	}

	@Override
	public Optional<Stations> retrieveStation(Integer id) {

		return stationsRepository.findById(id);
	}

	@Override
	public String deleteStation(Integer id) {

		if (stationsRepository.findById(id).isPresent()) {
			stationsRepository.deleteById(id);
			return "Deleted👍😎";

		} else {
			throw new ValidationException("Station with Id: " + id + " not found");
		}
	}

	@Override
	public Stations updateStation(Stations station) {

		Optional<Stations> existstation = stationsRepository.findById(station.getStationId());
		if (existstation.isEmpty()) {
			throw new ValidationException("Station Not found");
		}
		return stationsRepository.save(station);
	}

}
