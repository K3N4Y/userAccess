package kenayperez.useraccess.Controller;

import jakarta.validation.Valid;
import kenayperez.useraccess.Service.AuthenticationService;
import kenayperez.useraccess.Service.UserService;
import kenayperez.useraccess.dto.AuthResponse;
import kenayperez.useraccess.dto.LoginRequest;
import kenayperez.useraccess.dto.UserDTO;
import kenayperez.useraccess.entities.User;
import kenayperez.useraccess.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    public UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword());

        String tokenValue = authenticationService.generateToken(userDetails);

        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400L)
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
