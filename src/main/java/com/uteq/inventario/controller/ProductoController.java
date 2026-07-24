package com.uteq.inventario.controller;

import com.uteq.inventario.dto.ApiResponse;
import com.uteq.inventario.dto.ProductoRequest;
import com.uteq.inventario.dto.ProductoResponse;
import com.uteq.inventario.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public ApiResponse<List<ProductoResponse>> listar(Pageable pageable) {
        Page<ProductoResponse> pag = service.listarProductos(pageable);
        Map<String, Object> meta = new HashMap<>();
        meta.put("page", pag.getNumber());
        meta.put("size", pag.getSize());
        meta.put("totalElements", pag.getTotalElements());
        meta.put("totalPages", pag.getTotalPages());
        
        return new ApiResponse<>(true, pag.getContent(), "Listado obtenido correctamente", meta);
    }

    @PostMapping
    public ApiResponse<ProductoResponse> crear(@Valid @RequestBody ProductoRequest req) {
        ProductoResponse res = service.crearProducto(req);
        return new ApiResponse<>(true, res, "Producto creado correctamente", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        service.eliminarProducto(id);
        return new ApiResponse<>(true, null, "Producto eliminado correctamente", null);
    }
}
