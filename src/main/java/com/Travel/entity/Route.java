package com.Travel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Route {
	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
	private int routeId;
	@Size(min = 3, max = 35, message = "Route Name should be Min 2 and Max 35 : ")
	private String routeName;

	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Stations> stations;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public List<Stations> getStations() {
		return stations;
	}

	public void setStations(List<Stations> stations) {
		this.stations = stations;
	}

}
