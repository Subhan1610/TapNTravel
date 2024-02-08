package com.Travel.entity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int travelStatusId;
	private int checkInStationId;
	
	private String checkInStationName;
	private int checkOutStationId;
	
	private String checkOutStationName;

	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;

	@ManyToOne
	@JoinColumn(name = "tap_card_id")

	private TapCard tapCard;

	@ManyToOne
	@JoinColumn(name = "route_id")
	@JsonIgnore
	private Route route;
	@OneToOne(mappedBy = "travelStatus")
	private TransactionHistory transactionHistory;
	
	
	

	public int getTravelStatusId() {
		return travelStatusId;
	}

	public void setTravelStatusId(int travelStatusId) {
		this.travelStatusId = travelStatusId;
	}

	public int getCheckInStationId() {
		return checkInStationId;
	}

	public void setCheckInStationId(int checkInStationId) {
		this.checkInStationId = checkInStationId;
	}

	public String getCheckInStationName() {
		return checkInStationName;
	}

	public void setCheckInStationName(String checkInStationName) {
		this.checkInStationName = checkInStationName;
	}

	public int getCheckOutStationId() {
		return checkOutStationId;
	}

	public void setCheckOutStationId(int checkOutStationId) {
		this.checkOutStationId = checkOutStationId;
	}

	public String getCheckOutStationName() {
		return checkOutStationName;
	}

	public void setCheckOutStationName(String checkOutStationName) {
		this.checkOutStationName = checkOutStationName;
	}

	public TapCard getTapCard() {
		return tapCard;
	}

	public void setTapCard(TapCard tapCard) {
		this.tapCard = tapCard;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

}
