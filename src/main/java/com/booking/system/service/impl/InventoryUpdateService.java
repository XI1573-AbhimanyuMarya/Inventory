package com.booking.system.service.impl;

import com.booking.system.exceptions.RecordMissingException;
import com.booking.system.repository.SparePartRepository;
import com.booking.system.repository.enitty.SparePart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Slf4j
public class InventoryUpdateService {

    @Autowired
    private SparePartRepository sparePartRepository;

    public void sparePartBooked(String sparePartId) {
        SparePart sparePart = sparePartRepository.findById(sparePartId).orElseThrow(() -> new RecordMissingException("Spare Part Id Not FOund."));
        sparePart.setStockCount(sparePart.getStockCount() - 1);
        sparePartRepository.saveAndFlush(sparePart);
    }

    public void sparePartAdded(String sparePartId) {
        SparePart sparePart = sparePartRepository.findById(sparePartId).orElseThrow(() -> new RecordMissingException("Spare Part Id Not FOund."));
        sparePart.setStockCount(sparePart.getStockCount() + 1);
        sparePartRepository.saveAndFlush(sparePart);
    }


}
