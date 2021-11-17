package com.airplane.seatallocate;

public class SeatAllocation {

    /**
     * a seat next to an aisle
     * @param airPlane
     * @param startingPassengerId
     * @param totalPassengerCount
     * @return
     */
    public static int allocateAisleSeats(AirPlane airPlane, int startingPassengerId, int totalPassengerCount){

        int maxLineNumber = airPlane.getMaximumLines();

        //if there is only one Seat set or none then there is no Aisle seats
        if(airPlane == null || airPlane.getSeatSet().size() <= 1){
            return -1;
        }

        var seatSetSize = airPlane.getSeatSet().size();
        var lineNumber = 1;

        while(lineNumber <= maxLineNumber && startingPassengerId < totalPassengerCount){

            for(SeatSet seatSet: airPlane.getSeatSet()){

                var rows = seatSet.getRows();

                //in first seat set, last row is Aisle
                if(seatSet.getId() == 1 && lineNumber <= seatSet.getSeatLines()){
                    var seatRow = seatSet.getSeatRows().get(rows -1);
                    var seat = seatRow.getSeats().get(lineNumber - 1);
                    seat.setType(SeatType.AISLE);
                    seat.setPassengerNumber(startingPassengerId);
                    startingPassengerId++;

                }
                //in last seat set, only first row is Aisle
                else if(seatSet.getId() == seatSetSize && lineNumber <= seatSet.getSeatLines()){
                    var seatRow = seatSet.getSeatRows().get(0);
                    var seat = seatRow.getSeats().get(lineNumber - 1);
                    seat.setType(SeatType.AISLE);
                    seat.setPassengerNumber(startingPassengerId);
                    startingPassengerId++;

                }
                //in middle seat set both first row and last row is Aisle
                else {
                    if(lineNumber <= seatSet.getSeatLines()){
                        var seatRow = seatSet.getSeatRows().get(0);
                        var seat = seatRow.getSeats().get(lineNumber - 1);
                        seat.setType(SeatType.AISLE);
                        seat.setPassengerNumber(startingPassengerId);

                        startingPassengerId++;

                        //since two allocations happen here need to make sure total count is not exceeded
                        if(startingPassengerId <= totalPassengerCount){
                            seatRow = seatSet.getSeatRows().get(rows - 1);
                            seat = seatRow.getSeats().get(lineNumber - 1);
                            seat.setType(SeatType.AISLE);
                            seat.setPassengerNumber(startingPassengerId);
                        }

                        startingPassengerId++;

                    }
                }

            }
            lineNumber++;
        }
        return startingPassengerId;
    }

    /**
     * a seat next to a window
     * @param airPlane
     * @param startingPassengerId
     * @param totalPassengerCount
     * @return
     */
    public static int allocateWindowSeats(AirPlane airPlane, int startingPassengerId, int totalPassengerCount){

        if(airPlane == null || airPlane.getSeatSet().isEmpty()){
            System.out.println("Invalid Argument: Passenger Airplane should have seats");
            return -1;
        }

        var lineNumber = 1;
        var maxLineNumber = airPlane.getMaximumLines();

        while(lineNumber <= maxLineNumber && startingPassengerId < totalPassengerCount){

            for(SeatSet seatSet: airPlane.getSeatSet()){
                var rows = seatSet.getRows();
                //if there is only one seat set 1st and last rows are window seats
                if(airPlane.getSeatSet().size() == 1 && lineNumber <= seatSet.getSeatLines()){
                    var seatRows = seatSet.getSeatRows();

                    if(seatRows.size() > 1){
                        var firstRow = seatRows.get(0);
                        var seat = firstRow.getSeats().get(lineNumber - 1);
                        seat.setType(SeatType.WINDOW);
                        seat.setPassengerNumber(startingPassengerId);
                        startingPassengerId++;

                        //since two allocations happen here need to make sure total count is not exceeded
                        if(startingPassengerId <= totalPassengerCount){
                            var lastRow = seatRows.get(rows - 1);
                            seat = lastRow.getSeats().get(lineNumber - 1);
                            seat.setType(SeatType.WINDOW);
                            seat.setPassengerNumber(startingPassengerId);
                            startingPassengerId++;
                        }
                    }else {
                        var singleRow = seatRows.get(0);
                        var seat = singleRow.getSeats().get(lineNumber - 1);
                        seat.setType(SeatType.WINDOW);
                        seat.setPassengerNumber(startingPassengerId);
                        startingPassengerId++;
                    }

                }
                //if there are more than 1 seat sets 1st row of 1st seatset and last row of last seat set are window seats
                else{
                    if(seatSet.getId() == 1 && lineNumber <= seatSet.getSeatLines()){
                        var seatRows = seatSet.getSeatRows();
                        var firstRow = seatRows.get(0);
                        var seat = firstRow.getSeats().get(lineNumber - 1);
                        seat.setType(SeatType.WINDOW);
                        seat.setPassengerNumber(startingPassengerId);
                        startingPassengerId++;
                    }
                    if(seatSet.getId() == airPlane.getSeatSet().size() && lineNumber <= seatSet.getSeatLines()){
                        var seatRows = seatSet.getSeatRows();
                        var lastRow = seatRows.get(rows - 1);
                        var seat = lastRow.getSeats().get(lineNumber - 1);
                        seat.setType(SeatType.WINDOW);
                        seat.setPassengerNumber(startingPassengerId);
                        startingPassengerId++;
                    }
                }
            }
            lineNumber++;
        }
        return startingPassengerId;
    }

    /**
     * a seat in center rows
     * @param airPlane
     * @param startingPassengerId
     * @param totalPassengerCount
     */
    public static void allocateCenterSeats(AirPlane airPlane, int startingPassengerId, int totalPassengerCount){
        //Any order is allowed in middle seats
        //we will fill in order of seat set id 1 --> n
        for(SeatSet seatSet: airPlane.getSeatSet()){
            for(SeatRow seatRow: seatSet.getSeatRows()){

               for(Seat seat: seatRow.getSeats()) {
                   if(seat.getType() == null && startingPassengerId <= totalPassengerCount){
                       seat.setType(SeatType.CENTER);
                       seat.setPassengerNumber(startingPassengerId);
                       startingPassengerId++;
                   }
               }
            }
        }

    }
}
