package com.airplane.seatallocate;

import java.util.ArrayList;
import java.util.List;

public class AirPlane {

    private List<SeatSet> seatSet = new ArrayList<>();
    private int maximumLines;
    private int totalSeats;
    private int totalPassengerCount;

    public AirPlane() {
    }

    public AirPlane(List<SeatSet> seatSet) {
        this.seatSet = seatSet;
    }

    public List<SeatSet> getSeatSet() {
        return seatSet;
    }

    public void setSeatSet(SeatSet seatSet) {
        this.seatSet.add(seatSet);
    }

    public int getMaximumLines() {
        return maximumLines;
    }

    public void setMaximumLines(int maximumLines) {
        this.maximumLines = maximumLines;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalPassengerCount() {
        return totalPassengerCount;
    }

    public void setTotalPassengerCount(int totalPassengerCount) {
        this.totalPassengerCount = totalPassengerCount;
    }
}
