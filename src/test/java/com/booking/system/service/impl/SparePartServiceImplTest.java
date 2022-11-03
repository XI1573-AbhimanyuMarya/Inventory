package com.booking.system.service.impl;

import com.booking.system.model.request.SparePartRequest;
import com.booking.system.model.response.SparePartOperationResponse;
import com.booking.system.repository.SparePartRepository;
import com.booking.system.repository.enitty.SparePart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Optional;

import static com.booking.system.constant.ApplicationConstants.STATUS_SUCCESS;
import static com.booking.system.constant.ApplicationConstants.SUCCESS_CREATE_MESSAGE;

class SparePartServiceImplTest {

    @Autowired
    private SparePartServiceImpl sparePartService;

    @InjectMocks
    private SparePartRepository sparePartRepository;

    @Test
    void addSparePart() {
        SparePartOperationResponse response = sparePartService.addSparePart(new SparePartRequest("", "DUMMY", 5));
        Assert.notNull(response);
        Assertions.assertEquals(getSuccessResponse(), response);
    }


    private SparePartOperationResponse getSuccessResponse() {
        return new SparePartOperationResponse(STATUS_SUCCESS, SUCCESS_CREATE_MESSAGE);
    }

    @Test
    void fetchInventoryData() {
        Mockito.when(sparePartRepository.findAll()).thenReturn(Arrays.asList(new SparePart("a", "b", 4)));
        Assertions.assertNull(sparePartService.fetchInventoryData());
    }


    @Test
    void viewSparePart() {
        Mockito.when(sparePartRepository.findById("ABC")).thenReturn(Optional.of(new SparePart("ABC", "DESC", 5)));
        Assert.notNull(sparePartService.viewSparePart("ABC"));
    }
}