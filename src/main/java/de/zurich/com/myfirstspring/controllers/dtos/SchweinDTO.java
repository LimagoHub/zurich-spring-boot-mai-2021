package de.zurich.com.myfirstspring.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDTO {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @DecimalMin(value="10", inclusive=true)
    private int gewicht;
}
