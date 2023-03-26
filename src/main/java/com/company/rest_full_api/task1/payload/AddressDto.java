package com.company.rest_full_api.task1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {
    @NotNull
    private String street;
    @NotNull
    private String homeNumber;
}
