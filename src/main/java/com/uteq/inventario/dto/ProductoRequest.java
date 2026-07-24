package com.uteq.inventario.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoRequest {
    @NotBlank(message = "no debe estar vacio")
    private String nombre;

    @NotBlank(message = "no debe estar vacio")
    private String categoria;

    @Min(value = 0, message = "debe ser mayor o igual a 0")
    private Integer stock;

    @DecimalMin(value = "0.01", message = "debe ser mayor o igual a 0.01")
    private BigDecimal precio;
}
