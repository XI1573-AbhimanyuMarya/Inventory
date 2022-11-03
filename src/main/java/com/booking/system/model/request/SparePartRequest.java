package com.booking.system.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SparePartRequest {
    private String sparePartId;
    private String sparePartDescription;
    private Integer stockCount;
}
