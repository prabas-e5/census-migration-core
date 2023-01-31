package com.org.census.migration.controller;

import com.org.census.migration.model.AddBatchResponseDto;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.BatchDetailsRequestDto;
import com.org.census.migration.service.BatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class BatchDetailsController implements BatchDetailsApi{

    @Autowired
    BatchDetailsService batchDetailsService;

    @Override
    public ResponseEntity<Void> saveBatchDetails(BatchDetailsRequestDto batchDetailsRequestDto) {
        batchDetailsService.saveBatchDetails(batchDetailsRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public BatchDetailsDto getBatchDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                           String clientName, String batchName) {
        return batchDetailsService.getBatchDetails(sourceEHRName, targetEHRName, serviceLine, clientName, batchName);
    }

    @Override
    public List<BatchDetailsDto> getBatchDetailsList(String sourceEHRName, String targetEHRName, String serviceLine,
                                                     String clientName) {
        return batchDetailsService.getBatchDetailsList(sourceEHRName, targetEHRName, serviceLine, clientName);
    }

    @Override
    public boolean uploadFileValidation(String sourceEHRName, String targetEHRName, String serviceLine, String clientName,
                                    String batchName, String processName, MultipartFile sourceFile)
            throws IOException {
        return batchDetailsService.uploadFileValidation(sourceEHRName, targetEHRName, serviceLine,
                                                        clientName, batchName, processName, sourceFile);
    }

    @Override
    public AddBatchResponseDto initiateTransformation(String sourceEHRName, String targetEHRName, String serviceLine, String clientName,
                                                      String batchName) {
        return batchDetailsService.initiateTransformation(sourceEHRName, targetEHRName, serviceLine, clientName, batchName);
    }
}
