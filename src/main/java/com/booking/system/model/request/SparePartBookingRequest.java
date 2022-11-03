package com.booking.system.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SparePartBookingRequest {
    private String sparePartId;
    private String comments;
    private String createdBy;
}
