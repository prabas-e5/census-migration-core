package com.org.census.migration.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddBatchResponseDto {

    private UUID requestId;
}
