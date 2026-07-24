package com.uteq.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InventarioMercadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioMercadoApplication.class, args);
	}
}
