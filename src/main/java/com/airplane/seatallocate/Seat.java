package com.airplane.seatallocate;

public class Seat {
    private SeatType type;
    private int id;
    private int row;
    private int seatLine;
    private int passengerNumber;

    public Seat(int id, int row, int seatLine) {
        this.id = id;
        this.row = row;
        this.seatLine = seatLine;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatLine() {
        return seatLine;
    }

    public void setSeatLine(int seatLine) {
        this.seatLine = seatLine;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }
}
