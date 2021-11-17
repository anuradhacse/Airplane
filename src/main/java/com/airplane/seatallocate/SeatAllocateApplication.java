package com.airplane.seatallocate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SeatAllocateApplication {

	public static void main(String[] args) {

		AirPlane airplane = new AirPlane();
		int passengerNumberInQueue = 30;

		List<List<Integer>> input = new ArrayList<>();
		input.add(List.of(3,2));
		input.add(List.of(4,3));
		input.add(List.of(2,3));
		input.add(List.of(3,4));

		int maximumLines = 0;
		int totalSeats = 0;
		int seatSetId = 1;

		for(List<Integer> list: input){
			if(list.size() == 2){
				int rows = list.get(0);
				int lines = list.get(1);
				//calculate maximum lines in a seat set
				if(lines > maximumLines){
					maximumLines = lines;
				}
				if(rows > 0 && lines > 0){
					SeatSet seatSet = new SeatSet();
					seatSet.setId(seatSetId);
					seatSet.setRows(rows);
					seatSet.setSeatLines(lines);
					airplane.setSeatSet(seatSet);
					totalSeats += rows * lines;
					seatSetId++;
				} else {
					System.out.println("Invalid input for rows and lines");
					break;
				}

			} else{
				System.out.println("Invalid Input, Expect 2d Array: " + list);
			}

		}

		airplane.setMaximumLines(maximumLines);

		if(totalSeats < passengerNumberInQueue){
			System.out.println("Cannot onboard all Passengers.Total passengers: "+passengerNumberInQueue+". Total Seats In Plane: "+totalSeats);
			return;
		}

		var seatId = 1;
		for(SeatSet seatSet : airplane.getSeatSet()){
			System.out.println(seatSet.getId()+": rows:"+seatSet.getRows()+",lines:"+seatSet.getSeatLines());
			for(int i = 1; i<=seatSet.getRows(); i++){
				var seatRow = new SeatRow();
				for(int j = 1; j<=seatSet.getSeatLines(); j++){
					Seat seat = new Seat(seatId, i,j);
					seatRow.addSeats(seat);
					seatId+=1;
				}
				seatSet.setSeatRow(seatRow);
			}

		}

		var passengerNumber = SeatAllocation.allocateAisleSeats(airplane, 1, 30);

/*		for(SeatSet seatSet : airplane.getSeatSet()){
			for(SeatRow seatRow : seatSet.getSeatRows()){
				for(Seat seat: seatRow.getSeats()){
					if(seat.getType() != null ){
						System.out.println("Seat id: "+seat.getId()+", row:"+seat.getRow()+",line:"+seat.getSeatLine()
						+", passengerNumber:"+seat.getPassengerNumber());
					}
				}
			}
		}*/

		passengerNumber = SeatAllocation.allocateWindowSeats(airplane, passengerNumber, 30);

/*		for(SeatSet seatSet : airplane.getSeatSet()){
			for(SeatRow seatRow : seatSet.getSeatRows()){
				for(Seat seat: seatRow.getSeats()){
					if(seat.getType() == SeatType.WINDOW ){
						System.out.println("Seat id: "+seat.getId()+", row:"+seat.getRow()+",line:"+seat.getSeatLine()
								+", passengerNumber:"+seat.getPassengerNumber());
					}
				}
			}
		}*/

		SeatAllocation.allocateCenterSeats(airplane, passengerNumber, 30);

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

