package com.booking.taxt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Booker {

	
	
	public static void main(String[] args) {
        
		List<Taxi> taxis = createTaxis(4);
		
		char[] array = {'A','B','C','D','E','F'};
		List<Character> locList = Arrays.asList('A','B','C','D','E','F');
		Scanner scan = new Scanner(System.in);
		int id =1;
		while(true) {
		System.out.println("Enter below options:\n1-Book Taxi\n2-View Details");
		int option = scan.nextInt();
		switch (option) {
		case 1: 
		{
			int customerId = id++;
			System.out.println("customerId = "+customerId);
			System.out.print("Enter Pickup point:");
			char pickupLoc = scan.next().charAt(0);
			
			if(!locList.contains(pickupLoc)) {
				System.out.println("Wrong PickUp location entered");
				break;
			}
			
			int pickupPoint = locationPoint(pickupLoc);
			
			System.out.print("Enter Drop point:");
			char dropLoc = scan.next().charAt(0);
			if(!locList.contains(dropLoc)) {
				System.out.println("Wrong Drop location entered");
				break;
			}
			int dropPoint = locationPoint(dropLoc);
			System.out.println("Enter pickup time: ");
			int pickupTime = scan.nextInt();
			
			List<Taxi> availableTaxies = getAvailableTaxies(taxis,pickupTime,pickupPoint);
			if(availableTaxies.size()==0) {
				System.out.println("No Taxies Available, booking cancelled");
			}else {
			Collections.sort(availableTaxies,(a,b)->a.totalEarnings-b.totalEarnings);
			bookTaxi(customerId,pickupPoint,pickupTime,dropPoint,availableTaxies);
			}
			break;
		}
		case 2: {
			for (Taxi taxi : taxis) {
				taxi.viewTaxiStatus();
			}
		}
		default: {

		}
		}
	}
	}

	private static void bookTaxi(int customerId, int pickupPoint, int pickupTime, int dropPoint, List<Taxi> availableTaxies) {
        int minDistance = Integer.MAX_VALUE;
        int distanceBetweenpickUpandDrop = 0;
        int earning = 0;
        int nextfreeTime = 0;
        int nextPoint = 0;
        Taxi bookedTaxi = null;
        List<String> tripDetail = new LinkedList<String>();
        
        for (Taxi taxi : availableTaxies) {	
        	int distanceBetweenCustomerAndTaxi = Math.abs(pickupPoint-taxi.currentPosition)*15;
        	//System.out.println(taxi+"distanceBetweenCustomerAndTaxi - "+distanceBetweenCustomerAndTaxi+"minDistance - "+minDistance);
        	if(distanceBetweenCustomerAndTaxi<minDistance) {
                bookedTaxi = taxi;
                distanceBetweenpickUpandDrop = Math.abs(pickupPoint-dropPoint)*15;
                earning=(distanceBetweenpickUpandDrop-5)*10+100;
                int dropTime = pickupTime+distanceBetweenpickUpandDrop/15;
                nextfreeTime=dropTime;
                nextPoint=dropPoint;
                tripDetail.add(String.valueOf(taxi.taxiId));
                tripDetail.add(String.valueOf(customerId));
                tripDetail.add(String.valueOf(pickUpPointToLoc(pickupPoint)));
                tripDetail.add(String.valueOf(pickUpPointToLoc(dropPoint)));
                tripDetail.add(String.valueOf(pickupTime));
                tripDetail.add(String.valueOf(dropTime));
                tripDetail.add(String.valueOf(earning));
                minDistance=distanceBetweenCustomerAndTaxi;
                
                
        	}
        	

		}
        bookedTaxi.setTaxi(pickUpPointToLoc(dropPoint),nextfreeTime,bookedTaxi.totalEarnings+earning,tripDetail);
        System.out.println("Taxi " + bookedTaxi.taxiId + " booked");
	}

	public static char pickUpPointToLoc(int pickupPoint) {
		char loc = 'A';
		switch (pickupPoint) {
		case 1: {loc = 'A';break;}
		case 2: {loc = 'B';break;}
		case 3: {loc = 'C';break;}
		case 4: {loc = 'D';break;}
		case 5: {loc = 'E';break;}
		case 6: {loc = 'F';break;}
		}
		return loc;
	}

	private static List<Taxi> getAvailableTaxies(List<Taxi> taxis, int pickupTime, int pickupPoint) {
		List<Taxi> freeTaxies = new ArrayList<Taxi>();;
		for (Taxi taxi : taxis) {
			if(pickupTime>=taxi.freeTime && Math.abs(taxi.currentPosition - pickupPoint) <= (pickupTime - taxi.freeTime)) {
				freeTaxies.add(taxi);
			}
		}
		return freeTaxies;
	}

	private static List<Taxi> createTaxis(int i) {
		List<Taxi> taxies = new ArrayList<Taxi>();
		while(i>0) {
			i--;
			Taxi taxi = new Taxi();
			taxies.add(taxi);
		}
		return taxies;
	}

	private static int locationPoint(char pickupLoc) {
		int point = 0;
		switch (pickupLoc) {
		case 'A': {point = 1;break;}
		case 'B': {point = 2;break;}
		case 'C': {point = 3;break;}
		case 'D': {point = 4;break;}
		case 'E': {point = 5;break;}
		case 'F': {point = 6;break;}
		}
		return point;
	}

}
