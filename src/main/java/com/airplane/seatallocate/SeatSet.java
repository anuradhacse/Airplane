package com.airplane.seatallocate;

import java.util.ArrayList;
import java.util.List;

public class SeatSet {

    private int id;
    List<SeatRow> seatRows = new ArrayList<>();
    private int rows;
    private int seatLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }

    public void setSeatRow(SeatRow seatRow) {
        this.seatRows.add(seatRow);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatLines() {
        return seatLines;
    }

    public void setSeatLines(int seatLines) {
        this.seatLines = seatLines;
    }
}
