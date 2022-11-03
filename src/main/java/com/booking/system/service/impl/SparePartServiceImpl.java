package com.booking.system.service.impl;

import com.booking.system.model.request.SparePartBookingRequest;
import com.booking.system.model.request.SparePartRequest;
import com.booking.system.model.response.SparePartOperationResponse;
import com.booking.system.model.response.SparePartResponse;
import com.booking.system.model.response.SparePartsInventoryResponse;
import com.booking.system.repository.OrdersRepository;
import com.booking.system.repository.SparePartRepository;
import com.booking.system.repository.enitty.Orders;
import com.booking.system.repository.enitty.SparePart;
import com.booking.system.service.SparePartService;
import com.booking.system.transformer.OrderTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.booking.system.constant.ApplicationConstants.*;

@Service
@Slf4j
public class SparePartServiceImpl implements SparePartService {

    @Autowired
    private SparePartRepository sparePartRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderTransformer orderTransformer;

    @Autowired
    private InventoryUpdateService inventoryUpdateService;

    @Override
    public SparePartOperationResponse addSparePart(SparePartRequest sparePartRequest) {
        SparePart sparePart = sparePartRepository.saveAndFlush(new SparePart(sparePartRequest.getSparePartDescription(), sparePartRequest.getStockCount()));
        inventoryUpdateService.sparePartAdded(sparePart.getSparePartId());
        return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_CREATE_MESSAGE);
    }

    @Override
    public List<SparePartsInventoryResponse> fetchInventoryData() {
        List<SparePart> inventory = sparePartRepository.findAll();
        return inventory.stream().map(a -> new SparePartsInventoryResponse(a.getSparePartId(), a.getSparePartDescription(), a.getStockCount())).collect(Collectors.toList());
    }

    @Override
    public SparePartOperationResponse removeSparePartFromInventory(String sparePartId) {
        SparePart sparePart = sparePartRepository.findById(sparePartId).orElseThrow(() -> new NotFoundException("Record not Found ."));
        sparePartRepository.delete(sparePart);
        return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_DELETE_MESSAGE);
    }

    @Override
    public SparePartOperationResponse update(SparePartRequest sparePartRequest) {
        SparePart sparePart = sparePartRepository.findById(sparePartRequest.getSparePartId()).orElseThrow(() -> new NotFoundException("Record Not Found."));
        sparePart.setSparePartId(sparePart.getSparePartId());
        sparePart.setSparePartDescription(sparePart.getSparePartDescription());
        sparePart.setStockCount(sparePart.getStockCount());
        sparePartRepository.saveAndFlush(sparePart);
        return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_UPDATE_MESSAGE);
    }


    @Override
    public SparePartOperationResponse bookSparePart(SparePartBookingRequest sparePartBookingRequest) {
        SparePart sparePart = sparePartRepository.findByIdAndStockCountGreaterThan(sparePartBookingRequest.getSparePartId(), 0);
        if (Objects.nonNull(sparePart)) {
            Orders orders = orderTransformer.convertToOrder(sparePart, sparePartBookingRequest);
            Orders orderedItem = ordersRepository.saveAndFlush(orders);
            inventoryUpdateService.sparePartBooked(orderedItem.getSparePartId());
            return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_ORDER_BOOKED_MESSAGE);
        } else {
            throw new NotFoundException("Spare Part is Either not available or Not in Stock");
        }

    }

    @Override
    public SparePartResponse viewSparePart(String sparePartId) {
        SparePart sparePart = sparePartRepository.findById(sparePartId).orElseThrow(() -> new NotFoundException("Record Not Found."));
        return new SparePartResponse(sparePart.getSparePartId(), sparePart.getSparePartDescription(), sparePart.getStockCount());
    }

    @Override
    public SparePartOperationResponse bookBulkSpareParts(List<SparePartBookingRequest> bookingRequests) {
        for (SparePartBookingRequest request : bookingRequests) {
            SparePart sparePart = sparePartRepository.findById(request.getSparePartId()).orElseThrow(() -> new NotFoundException("Record Not Found."));
            Orders orders = orderTransformer.convertToOrder(sparePart, request);
            Orders orderedItem = ordersRepository.saveAndFlush(orders);
            inventoryUpdateService.sparePartBooked(orderedItem.getSparePartId());
        }
        return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_ORDER_BOOKED_MESSAGE);
    }
}
