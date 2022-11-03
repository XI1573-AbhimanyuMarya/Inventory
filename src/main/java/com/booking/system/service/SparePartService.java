package com.booking.system.service;

import com.booking.system.model.request.SparePartBookingRequest;
import com.booking.system.model.request.SparePartRequest;
import com.booking.system.model.response.SparePartOperationResponse;
import com.booking.system.model.response.SparePartsInventoryResponse;

import java.util.List;

public interface SparePartService {
    SparePartOperationResponse addSparePart(SparePartRequest sparePartRequest);

    List<SparePartsInventoryResponse> fetchInventoryData();

    SparePartOperationResponse removeSparePartFromInventory(String sparePartId);

    SparePartOperationResponse update(SparePartRequest sparePartRequest);

    SparePartOperationResponse bookSparePart(SparePartBookingRequest sparePartBookingRequest);
}
