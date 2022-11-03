package com.booking.system.transformer;

import com.booking.system.model.request.SparePartBookingRequest;
import com.booking.system.repository.enitty.Orders;
import com.booking.system.repository.enitty.SparePart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class OrderTransformer {

    public Orders convertToOrder(SparePart sparePart, SparePartBookingRequest sparePartBookingRequest) {
        return Orders.builder()
                .orderDescription(sparePartBookingRequest.getComments())
                .sparePartId(sparePart.getSparePartId())
                .createdBy(sparePartBookingRequest.getCreatedBy())
                .createdOn(LocalDateTime.now())
                .build();
    }
}
