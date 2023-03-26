package com.company.rest_full_api.task1.payload;

import com.company.rest_full_api.task1.entity.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull
    private String corpName;
    @NotNull
    private String directorName;
    @NotNull
    private Integer addressId;
}
