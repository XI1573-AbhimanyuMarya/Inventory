package com.booking.system.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingRequest {
    @JsonProperty("bookingRequests")
    private List<SparePartBookingRequest> bookingRequests;
}
