package kenayperez.useraccess.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String username;
    private LocalDateTime timestamp;

    public UserDTO(String email, String username) {
        this.email = email;
        this.username = username;
        this.timestamp = LocalDateTime.now();
    }

}
