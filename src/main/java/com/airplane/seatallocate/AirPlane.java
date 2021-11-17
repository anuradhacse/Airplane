package com.airplane.seatallocate;

import java.util.ArrayList;
import java.util.List;

public class AirPlane {

    private List<SeatSet> seatSet = new ArrayList<>();
    private int maximumLines;

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
}
