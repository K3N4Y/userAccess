package kenayperez.useraccess.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private boolean error;
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public  ErrorResponse(String code, String message) {
        this.error = true;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}



