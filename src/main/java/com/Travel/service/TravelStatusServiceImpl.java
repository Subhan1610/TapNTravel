package com.Travel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Travel.entity.Route;
import com.Travel.entity.Stations;
import com.Travel.entity.TapCard;
import com.Travel.entity.TravelStatus;
import com.Travel.entity.TransactionHistory;
import com.Travel.exception.ValidationException;
import com.Travel.repository.RouteRepository;
import com.Travel.repository.StationsRepository;
import com.Travel.repository.TapCardRepository;
import com.Travel.repository.TravelStausRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Travel.repository.TransactionHistoryRepository;

@Service
public class TravelStatusServiceImpl implements TravelStatusService {

	@Autowired
	private TravelStausRepository travelStausRepository;

	@Autowired
	private TapCardRepository cardRepository;

	@Autowired
	private StationsRepository stationsRepository;
	@Autowired
	private TransactionHistoryRepository transactionRepository;
	@Autowired
	private RouteRepository routeRepository;

	@Override
	public Optional<TravelStatus> retrieveTravelStatus(Integer id) {

		Optional<TravelStatus> rouitee = travelStausRepository.findById(id);

		if (rouitee.isEmpty())
			throw new ValidationException("id-" + id);

		return rouitee;
	}

//	@PostMapping(value = "checkIn")

	public String checkInTravelStatus(int tabCardId, String inStationName) throws JsonProcessingException {
		TravelStatus travelStatus = null;
		double minimumBalanceRequired = 100;
		Optional<TapCard> cardOptional = cardRepository.findById(tabCardId);
		if (cardOptional.isEmpty()) {
			throw new ValidationException("Tap Card Not Found");
		}
		TapCard card = cardOptional.get();
		if (card.getTabBalance() < minimumBalanceRequired) {
			throw new ValidationException("Insufficient balance. Minimum balance required: " + minimumBalanceRequired);
		}
		if (!card.getStatus().equalsIgnoreCase("ON JOURNEY")) {
			List<Stations> stations = stationsRepository.findAllByStationName(inStationName);

			if (stations.size() > 0) {

				travelStatus = new TravelStatus();
				travelStatus.setCheckInStationName(inStationName);

				// Capture the check-in time
				LocalDateTime checkInTime = LocalDateTime.now();
				travelStatus.setCheckInTime(checkInTime);

				card.setStatus("ON JOURNEY");
				travelStatus.setTapCard(card);
				travelStausRepository.save(travelStatus);

			}
			if (travelStatus != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonString = objectMapper.writeValueAsString(new MessageResponse("CheckIn"));
				return jsonString;
			} else {
				throw new ValidationException("Something wrong with station name");
			}

		} else {
			throw new ValidationException("Already Journey Was Initiated from Station. ");
		}

	}
//	@Override
//	public TravelStatus checkInTravelStatus(int tabCardId, int stationId) {
//		
//		TapCard card = cardRepository.findById(tabCardId).get();
//		
//		card.setStatus("On Journey");
//
//		Optional<Stations> optional = stationsRepository.findById(stationId);
//		TravelStatus travelStatus = new TravelStatus();
//		if (optional.isPresent()) {
//			travelStatus.setCheckInStationId(stationId);
//			travelStatus.setCheckInStationName(optional.get().getStationName());
//			travelStatus.setTapCard(card);
//		}
//		return travelStausRepository.save(travelStatus);
//	}

	public String checkOutTravelStatus(int tabCardId, String outStationName) throws JsonProcessingException {

		int totalFare = 0;
		int totalStationsTravelled = 0;
		Optional<TapCard> tapCardOptional = cardRepository.findById(tabCardId);
		if (tapCardOptional.isPresent()) {

			TapCard tapCard = tapCardOptional.get();
			if (tapCard.getStatus().equalsIgnoreCase("ON JOURNEY")) {
				Route traveledRoute = null;

				TravelStatus travel = tapCard.getTravelStatusList().get(tapCard.getTravelStatusList().size() - 1);

				Stations inStation = new Stations();
				Stations outStation = new Stations();
				List<Stations> outStations = stationsRepository.findAllByStationName(outStationName);

				if (outStations.size() > 0) {

					List<Route> allRoutes = routeRepository.findAll();

					for (Route route : allRoutes) {
						boolean containsInStation = false;
						boolean containsOutStation = false;

						for (Stations station : route.getStations()) {

							if (station.getStationName().equalsIgnoreCase(travel.getCheckInStationName())) {

								containsInStation = true;
							}
							if (station.getStationName().equalsIgnoreCase(outStationName)) {

								containsOutStation = true;

							}
							if (containsInStation && containsOutStation) {

								traveledRoute = route;
							}

						}

					}
					inStation = stationsRepository.findByStationNameAndRoute(travel.getCheckInStationName(),
							traveledRoute);
					outStation = stationsRepository.findByStationNameAndRoute(outStationName, traveledRoute);
					if (inStation != null && outStation != null) {
						// -----------------------------------------------------
						travel.setCheckInStationId(inStation.getStationId());

						travel.setCheckOutStationId(outStation.getStationId());
						travel.setCheckOutStationName(outStation.getStationName());

						LocalDateTime checkOutTime = LocalDateTime.now();
						travel.setCheckOutTime(checkOutTime);
						travel.setRoute(traveledRoute);

						// fare part-------------------------------------
						TapCard card = travel.getTapCard();
						int orderOfInStation = inStation.getStationOrderId();
						int orderOfOutStation = outStation.getStationOrderId();
						totalStationsTravelled = Math.abs((orderOfInStation - orderOfOutStation));
						totalFare = totalStationsTravelled * 10;// ₹10 for each station
						int closingBalance = card.getTabBalance() - totalFare;

						// Transaction Wallet part

						if (closingBalance > 0) {
							TransactionHistory transaction = new TransactionHistory();
							transaction.setDebitAmount(totalFare);
							transaction.setCreditAmount(0);
							transaction.setClosingBalance(card.getTabBalance() - totalFare);
							transaction.setTapCard(card);
							transaction.setTravelStatus(travel);
							transactionRepository.save(transaction);

							card.getWalletTransactions().add(transaction);

							card.setTabBalance(card.getTabBalance() - totalFare);
							card.setStatus("JOURNEY COMPLETED");
							travelStausRepository.save(travel);
							cardRepository.save(card);

						} else {
							throw new ValidationException("Under Low Balance Please Recharge....");
						}
					} else {
						throw new ValidationException("You are on the Wrong route");
					}
				}
				if (traveledRoute != null) {

					String response = "Checkout Successful" + "\nInstation::  " + inStation.getStationName()
							+ "\nOutstation:: " + outStationName + "\nTotalStation:: " + totalStationsTravelled
							+ "\nYou have been charged:₹ " + totalFare;
// Create a JSON object with the provided message
					ObjectMapper objectMapper = new ObjectMapper();
					String jsonString = objectMapper.writeValueAsString(new MessageResponse(response));
					return jsonString;
				} else {
					throw new ValidationException("Something wrong with station name");
				}

			} else {
				throw new ValidationException("Journey not Started yet");
			}
		} else {
			throw new ValidationException("Tap card Not Found!!!");
		}
	}

//	@Override
//	public TravelStatus checkOutTravelStatus(int travelStatusId, int stationId) {
//
//		TravelStatus travelStatus1 = travelStausRepository.findById(travelStatusId).get();
//
//		Optional<Stations> optional = stationsRepository.findById(stationId);
//
//		if (optional.isPresent()) {
//			Stations stations = optional.get();
//			travelStatus1.setCheckOutStationId(stationId);
//			travelStatus1.setCheckOutStationName(stations.getStationName());
//			travelStatus1.setRoute(stations.getRoute());
//
//			TapCard card = travelStatus1.getTapCard();
//			if (card != null) {
//				card.getStatus();
//			} else {
//				throw new UserInfoException("No associated TapCard found.");
//			}
//			card.setStatus("Journey Completed");
//		} else {
//			throw new UserInfoException("Invalid stationId: " + stationId);
//		}
//
//		return travelStausRepository.save(travelStatus1);
//	}

//	@Override
//	public TravelStatus checkOutTravelStatus(int tabCardId, int stationId) {
//
//		TapCard card = cardRepository.findById(tabCardId).get();
//
//		card.setStatus("Journey Completed");
//
//		Optional<Stations> optional = stationsRepository.findById(stationId);
//		TravelStatus travelStatus = new TravelStatus();
//		if (optional.isPresent()) {
//			travelStatus.setCheckOutStationId(stationId);
//			travelStatus.setCheckOutStationName(optional.get().getStationName());
//			travelStatus.setRoute(optional.get().getRoute());
//		}
//		return travelStausRepository.save(travelStatus);
//	}

	@Override
	public List<TravelStatus> retrieveAllTravelStatus() {

		return travelStausRepository.findAll();
	}

//	@Override
//	public void deleteTravelStatus(Integer id) {
//
//		travelStausRepository.deleteById(id);
//
//	}

	static class MessageResponse {
		private String message;

		public MessageResponse(String message) {
			this.message = message;
		}

		// Getter and setter for Jackson
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
