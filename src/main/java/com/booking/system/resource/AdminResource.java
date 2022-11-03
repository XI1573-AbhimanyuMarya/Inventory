package com.booking.system.resource;

import com.booking.system.model.request.SparePartRequest;
import com.booking.system.model.response.SparePartOperationResponse;
import com.booking.system.model.response.SparePartsInventoryResponse;
import com.booking.system.service.SparePartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.booking.system.constant.ApplicationConstants.HTTP_INTERNAL_SERVER_ERROR;
import static com.booking.system.constant.ApplicationConstants.HTTP_OK;

@RestController
@RequestMapping("/api/admin/sparePart")
@Slf4j
public class AdminResource {

    @Autowired
    private SparePartService sparePartServiceImpl;

    @Operation(summary = "Add Spare Part", description = "Add Spare Parts in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Added Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to Create Spare Part.",
                    content = {@Content()})
    })
    @PostMapping("/v1/create")
    public SparePartOperationResponse addSparePart(@RequestBody SparePartRequest sparePartRequest) {
        log.info("Request received to get add spare part: {} in Inventory.", sparePartRequest.getSparePartDescription());
        return sparePartServiceImpl.addSparePart(sparePartRequest);
    }


    @Operation(summary = "View Spare Part", description = "View Spare Parts in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part retrieved Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to fetch Spare Parts.",
                    content = {@Content()})
    })
    @GetMapping
    public List<SparePartsInventoryResponse> fetch() {
        log.info("Request received to fetch details of spare part in Inventory.");
        return sparePartServiceImpl.fetchInventoryData();
    }


    @Operation(summary = "Delete Spare Part", description = "Delete Spare Part in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Deleted Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to Delete Spare Part.",
                    content = {@Content()})
    })
    @DeleteMapping("/v1/{sparePartId}")
    public SparePartOperationResponse removeSparePart(@PathVariable("sparePartId") String sparePartId) {
        log.info("Request received to remove Spare Part with Id :{} from Inventory.", sparePartId);
        return sparePartServiceImpl.removeSparePartFromInventory(sparePartId);
    }


    @Operation(summary = "Update Spare Part", description = "Update Spare Parts Details in Inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_OK, description = "Spare Part Updated Successfully .",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = HTTP_INTERNAL_SERVER_ERROR, description = "Internal Server Error : Failed to Create Spare Part.",
                    content = {@Content()})
    })
    @PutMapping("/v1/update")
    public SparePartOperationResponse updateSparePartDetails(@RequestBody SparePartRequest sparePartRequest) {
        log.info("Request received to Update spare part with Id: {} in Inventory.", sparePartRequest.getSparePartId());
        return sparePartServiceImpl.update(sparePartRequest);
    }


}
