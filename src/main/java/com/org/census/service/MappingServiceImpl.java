package com.org.census.service;

import com.org.census.entity.EHRMapping;
import com.org.census.model.MappingResponseDto;
import com.org.census.repository.EHRMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private EHRMappingRepository ehrMappingRepository;

    @Override
    public MappingResponseDto getOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                       String clientName) {

        List<EHRMapping> responseList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, clientName);
        MappingResponseDto mappingResponseDto = new MappingResponseDto();
        responseList.forEach(System.out::println);
//        mappingResponseDto.setServiceLine(serviceLine);
        return mappingResponseDto;
    }
}
