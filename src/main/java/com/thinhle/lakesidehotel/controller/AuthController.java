package com.thinhle.lakesidehotel.controller;

import com.thinhle.lakesidehotel.exception.UserAlreadyExistsException;
import com.thinhle.lakesidehotel.model.User;
import com.thinhle.lakesidehotel.request.LoginRequest;
import com.thinhle.lakesidehotel.security.jwt.JwtUtils;
import com.thinhle.lakesidehotel.service.IUserService;
import com.thinhle.lakesidehotel.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(User user) {
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch(UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            return null;
    }
}
