package com.booking.system.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SparePartsInventoryResponse {
    private String sparePartId;
    private String sparePartDescription;
    private Integer stockCount;
}
