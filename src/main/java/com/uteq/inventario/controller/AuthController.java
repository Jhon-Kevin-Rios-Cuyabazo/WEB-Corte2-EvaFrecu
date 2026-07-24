package com.uteq.inventario.controller;

import com.uteq.inventario.dto.ApiResponse;
import com.uteq.inventario.entity.Usuario;
import com.uteq.inventario.repository.UsuarioRepository;
import com.uteq.inventario.security.JwtTokenProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest request) {
        Usuario u = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                
        if (!encoder.matches(request.getPassword(), u.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }
        
        String token = tokenProvider.generarToken(u.getUsername(), u.getRol());
        return new ApiResponse<>(true, token, "Login exitoso", null);
    }
}

@Data
class LoginRequest {
    private String username;
    private String password;
}
