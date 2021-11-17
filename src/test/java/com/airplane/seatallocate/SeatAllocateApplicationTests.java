package com.airplane.seatallocate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.airplane.seatallocate.SeatType.*;

@SpringBootTest
class SeatAllocateApplicationTests {

	@Test
	public void testSeatAllocation() {

		//Aisle seat allocation
		AirPlane airplane = AirPlaneBuilder.build(new String[]{"3,2", "4,3", "2,3", "3,4", "30"});
		var passengerId = SeatAllocationUtil.allocateAisleSeats(airplane, 1,
				airplane.getTotalPassengerCount() );

		var seatSet = airplane.getSeatSet().get(0);
		var seatRows = seatSet.getSeatRows();
		Assertions.assertEquals(AISLE, seatRows.get(2).getSeats().get(0).getType());
		Assertions.assertEquals(1, seatRows.get(2).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(AISLE, seatRows.get(2).getSeats().get(1).getType());
		Assertions.assertEquals(7, seatRows.get(2).getSeats().get(1).getPassengerNumber());

		seatSet = airplane.getSeatSet().get(1);
		seatRows = seatSet.getSeatRows();
		Assertions.assertEquals(AISLE, seatRows.get(0).getSeats().get(0).getType());
		Assertions.assertEquals(2, seatRows.get(0).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(AISLE, seatRows.get(0).getSeats().get(1).getType());
		Assertions.assertEquals(8, seatRows.get(0).getSeats().get(1).getPassengerNumber());
		Assertions.assertEquals(AISLE, seatRows.get(0).getSeats().get(2).getType());
		Assertions.assertEquals(13, seatRows.get(0).getSeats().get(2).getPassengerNumber());

		Assertions.assertEquals(AISLE, seatRows.get(3).getSeats().get(0).getType());
		Assertions.assertEquals(3, seatRows.get(3).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(AISLE, seatRows.get(3).getSeats().get(1).getType());
		Assertions.assertEquals(9, seatRows.get(3).getSeats().get(1).getPassengerNumber());

		//window seat allocation
		passengerId = SeatAllocationUtil.allocateWindowSeats(airplane, passengerId,
				airplane.getTotalPassengerCount() );
		seatSet = airplane.getSeatSet().get(0);
		seatRows = seatSet.getSeatRows();
		Assertions.assertEquals(WINDOW, seatRows.get(0).getSeats().get(0).getType());
		Assertions.assertEquals(19, seatRows.get(0).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(WINDOW, seatRows.get(0).getSeats().get(1).getType());
		Assertions.assertEquals(21, seatRows.get(0).getSeats().get(1).getPassengerNumber());

		seatSet = airplane.getSeatSet().get(3);
		seatRows = seatSet.getSeatRows();
		Assertions.assertEquals(WINDOW, seatRows.get(2).getSeats().get(0).getType());
		Assertions.assertEquals(20, seatRows.get(2).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(WINDOW, seatRows.get(2).getSeats().get(1).getType());
		Assertions.assertEquals(22, seatRows.get(2).getSeats().get(1).getPassengerNumber());

		//window seat allocation - for center seats there is no order.
		SeatAllocationUtil.allocateCenterSeats(airplane, passengerId,
				airplane.getTotalPassengerCount() );
		seatSet = airplane.getSeatSet().get(1);
		seatRows = seatSet.getSeatRows();
		Assertions.assertEquals(CENTER, seatRows.get(1).getSeats().get(0).getType());
		Assertions.assertEquals(27, seatRows.get(1).getSeats().get(0).getPassengerNumber());
		Assertions.assertEquals(CENTER, seatRows.get(1).getSeats().get(1).getType());
		Assertions.assertEquals(28, seatRows.get(1).getSeats().get(1).getPassengerNumber());

	}

	//todo write tests for invalid scenarios and edge cases
}
