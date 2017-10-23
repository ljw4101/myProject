package com.airbnb.web.domain;

import org.springframework.stereotype.Component;

@Component
public class Reservation {
	private String 
		rsvSeq,
		checkin,
		checkout,
		regdate,
		adult,
		teen,
		child,
		solddays,
		hostSerial,
		memberId,
		resPrice;
}
