package com.booking.taxt;

import java.util.ArrayList;
import java.util.List;

public class Taxi {

	static int taxiCount=0;
	int currentPosition;
	int freeTime;
	int totalEarnings;
	List<String> totalBookings;
	int taxiId;
	
	public Taxi() {
        currentPosition = 1;
        freeTime = 5;
        totalEarnings = 0;
        totalBookings = new ArrayList<String>();
        taxiId = ++taxiCount;
        
	}

	public void setTaxi(char currentSpot, int nextfreeTime, int earnings, String tripDetail) {
        this.currentPosition = Booker.pickUpPointToLoc(currentSpot);
        this.freeTime = nextfreeTime;
        this.totalEarnings = earnings;
        this.totalBookings.add(tripDetail);		
	}
	
	public void viewTaxiStatus() {
		for (String string : totalBookings) {
			System.out.println(string);			
		}
	}
	
}
