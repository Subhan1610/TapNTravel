package com.Travel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stations {

	@Id
	private int stationId;
	@Size(min = 3, max = 40, message = "Station Name should be Min 3 and Max 40 : ")
	private String stationName;
	private int stationOrderId;
	private String stationLocation; // New field for location

    private String stationFacility; // New field for facility
	@ManyToOne
	@JoinColumn(name = "route_id")
	@JsonBackReference
	private Route route;

	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TravelStatus> travelStatusList;

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getStationOrderId() {
		return stationOrderId;
	}

	public void setStationOrderId(int stationOrderId) {
		this.stationOrderId = stationOrderId;
	}

	public List<TravelStatus> getTravelStatusList() {
		return travelStatusList;
	}

	public void setTravelStatusList(List<TravelStatus> travelStatusList) {
		this.travelStatusList = travelStatusList;
	}

}
