package com.airplane.seatallocate;

import java.util.ArrayList;
import java.util.List;

public class AirPlaneBuilder {

    public static AirPlane build(String[] args) {

        List<List<Integer>> input = new ArrayList<>();

        for (int i=0; i<args.length -1;++i) {
            List<Integer> argList = new ArrayList<>();
            for (String arg : args[i].split(",")) {
                argList.add(Integer.valueOf(arg));
            }
            input.add(argList);
        }

        AirPlane airplane = new AirPlane();
        int passengerNumberInQueue = Integer.parseInt(args[args.length -1]);
        airplane.setTotalPassengerCount(passengerNumberInQueue);

        //todo : input argument validations

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
        airplane.setTotalSeats(totalSeats);

        if(totalSeats < passengerNumberInQueue){
            System.out.println("Cannot onboard all Passengers.Total passengers: "+passengerNumberInQueue+". Total Seats In Plane: "+totalSeats);
            return null;
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
        return airplane;
    }
}
