package com.uteq.inventario.service;

import com.uteq.inventario.dto.ProductoRequest;
import com.uteq.inventario.dto.ProductoResponse;
import com.uteq.inventario.entity.Producto;
import com.uteq.inventario.exception.ResourceNotFoundException;
import com.uteq.inventario.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    @Cacheable(value = "productos", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProductoResponse> listarProductos(Pageable pageable) {
        return repository.findByActivoTrue(pageable).map(this::mapToResponse);
    }

    @CacheEvict(value = "productos", allEntries = true)
    public ProductoResponse crearProducto(ProductoRequest request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        p.setCategoria(request.getCategoria());
        p.setStock(request.getStock());
        p.setPrecio(request.getPrecio());
        return mapToResponse(repository.save(p));
    }

    @CacheEvict(value = "productos", allEntries = true)
    public void eliminarProducto(Long id) {
        Producto p = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        p.setActivo(false);
        repository.save(p);
    }

    private ProductoResponse mapToResponse(Producto p) {
        ProductoResponse res = new ProductoResponse();
        res.setId(p.getId());
        res.setNombre(p.getNombre());
        res.setCategoria(p.getCategoria());
        res.setStock(p.getStock());
        res.setPrecio(p.getPrecio());
        res.setActivo(p.getActivo());
        res.setCreadoEn(p.getCreadoEn());
        return res;
    }
}
