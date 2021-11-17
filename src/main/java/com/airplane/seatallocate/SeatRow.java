package com.airplane.seatallocate;

import java.util.ArrayList;
import java.util.List;

public class SeatRow {

    private List<Seat> seats = new ArrayList<>();

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeats(Seat seat) {
        this.seats.add(seat);
    }
}
