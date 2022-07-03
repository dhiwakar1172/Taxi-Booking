package com.booking.taxt;

import java.util.ArrayList;
import java.util.List;

public class Taxi {

	static int taxiCount = 0;
	int currentPosition;
	int freeTime;
	int totalEarnings;
	List<List<String>> totalBookings;
	int taxiId;

	public Taxi() {
		currentPosition = 1;
		freeTime = 5;
		totalEarnings = 0;
		totalBookings = new ArrayList<List<String>>();
		taxiId = ++taxiCount;

	}

	public void setTaxi(char currentSpot, int nextfreeTime, int earnings, List<String> tripDetail) {
		this.currentPosition = Booker.pickUpPointToLoc(currentSpot);
		this.freeTime = nextfreeTime;
		this.totalEarnings = earnings;
		this.totalBookings.add(tripDetail);
	}

	public void viewTaxiStatus() {
		for (List list : totalBookings) {
			System.out.println("Taxi ID : " + list.get(0) + " Customer ID : " + list.get(1) + " PickupPoint : "
					+ list.get(2) + " Drop point : " + list.get(3)+" Pickup Time : "+list.get(4)+" Drop time : "+list.get(5)+" Earning : "+list.get(6));
		}
	}

}
