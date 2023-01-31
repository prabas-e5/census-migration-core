package com.org.census.migration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDetailsRequestDto {

    @NotNull
    private String sourceEhrName;

    @NotNull
    private String targetEhrName;

    @NotNull
    private String serviceLine;

    @NotNull
    private String clientName;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date goLiveDate;
}
