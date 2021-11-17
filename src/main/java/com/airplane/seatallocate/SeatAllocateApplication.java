package com.airplane.seatallocate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SeatAllocateApplication {

	public static void main(String[] args) {

		AirPlane airplane = AirPlaneBuilder.build(args);
		int totalPassengerCount = airplane.getTotalPassengerCount();

		var passengerId = SeatAllocation.allocateAisleSeats(airplane, 1,totalPassengerCount );
		passengerId = SeatAllocation.allocateWindowSeats(airplane, passengerId, totalPassengerCount);
		SeatAllocation.allocateCenterSeats(airplane, passengerId, totalPassengerCount);

		System.out.println("Seat set Ids are increasing from left to right: 1 , 2 , 3 , 4 .....");
		for(SeatSet seatSet : airplane.getSeatSet()){
			System.out.println("============== Seat Set ID: " + seatSet.getId()+ " ==================");
			for(SeatRow seatRow : seatSet.getSeatRows()){
				for(Seat seat: seatRow.getSeats()){
					if(seat.getPassengerNumber() > 0){
						System.out.println("Passenger : "+seat.getPassengerNumber() + " ==> "
								+ " Seat Type: " + seat.getType() +" ,your seat is in row: " + seat.getRow()
								+" in line: "+seat.getSeatLine());
					}
				}
			}
			System.out.println("\n");
		}
	}

}

