package com.uteq.inventario.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String categoria;
    private Integer stock;
    private BigDecimal precio;
    private Boolean activo;
    private ZonedDateTime creadoEn;
}
