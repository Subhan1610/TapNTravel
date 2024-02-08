package com.Travel.service;

import java.util.List;
import java.util.Optional;

import com.Travel.entity.TravelStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TravelStatusService {

	public abstract String checkInTravelStatus(int tabCardId, String inStationName)throws JsonProcessingException;

	public abstract String checkOutTravelStatus(int tabCardId, String outStationName)throws JsonProcessingException;

	public abstract List<TravelStatus> retrieveAllTravelStatus();

	public abstract Optional<TravelStatus> retrieveTravelStatus(Integer id);

	// public void deleteTravelStatus(Integer id);

}
