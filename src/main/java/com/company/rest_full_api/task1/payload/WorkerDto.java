package com.company.rest_full_api.task1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Integer addressId;
    @NotNull
    private Integer companyId;

}
