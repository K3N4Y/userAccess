package kenayperez.useraccess.Controller;

import jakarta.validation.Valid;
import kenayperez.useraccess.Service.UserService;
import kenayperez.useraccess.dto.UserDTO;
import kenayperez.useraccess.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user) {

    }
}
