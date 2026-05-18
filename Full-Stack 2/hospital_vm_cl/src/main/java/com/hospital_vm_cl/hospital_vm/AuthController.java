package com.hospital_vm_cl.hospital_vm;

import com.hospital_vm_cl.hospital_vm.dto.LoginRequest;
import com.hospital_vm_cl.hospital_vm.dto.AuthResponse;
import com.hospital_vm_cl.hospital_vm.service.UserService;
import com.hospital_vm_cl.hospital_vm.service.JwtService;
import com.hospital_vm_cl.hospital_vm.model.User;
import com.hospital_vm_cl.hospital_vm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        User user = userService.findByUsername(request.getUsername());

        if (user == null || !request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        userRepository.save(user);
        
        return ResponseEntity.ok("Usuario Creado con éxito");
    }
}