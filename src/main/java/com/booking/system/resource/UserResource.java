package com.booking.system.resource;

import com.booking.system.model.request.BookingRequest;
import com.booking.system.model.request.SparePartBookingRequest;
import com.booking.system.model.response.SparePartOperationResponse;
import com.booking.system.model.response.SparePartResponse;
import com.booking.system.service.SparePartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.booking.system.constant.ApplicationConstants.HTTP_INTERNAL_SERVER_ERROR;
import static com.booking.system.constant.ApplicationConstants.HTTP_OK;

@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserResource {


    @Autowired
    private SparePartService sparePartServiceImpl;

    @Operation(summary = "Book Spare Part", description = "Book Spare Part in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Added Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to Create Spare Part.",
                    content = {@Content()})
    })
    @PostMapping("/v1/book")
    public SparePartOperationResponse bookSparePart(@RequestBody SparePartBookingRequest sparePartBookingRequest) {
        log.info("Request received to get book spare part: {} from Inventory.", sparePartBookingRequest.getSparePartId());
        return sparePartServiceImpl.bookSparePart(sparePartBookingRequest);
    }


    @Operation(summary = "Book Spare Parts", description = "Book Spare Part in bulk in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Added Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to Create Spare Part.",
                    content = {@Content()})
    })
    @PostMapping("/v1/bulk/book")
    public SparePartOperationResponse bookSparePartsInBulk(@RequestBody BookingRequest bookingRequest) {
        log.info("Request received to book spare parts from Inventory.");
        return sparePartServiceImpl.bookBulkSpareParts(bookingRequest.getBookingRequests());
    }


    @Operation(summary = "View Single Spare Part", description = "View Single Spare Part in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Retrived Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to retrive Spare Part.",
                    content = {@Content()})
    })
    @GetMapping("/v1/sparePart")
    public SparePartResponse viewSparePart(@RequestParam("sparePartId") String sparePartId) {
        log.info("Request received to view spare part against Id: {} from Inventory.", sparePartId);
        return sparePartServiceImpl.viewSparePart(sparePartId);
    }


}
