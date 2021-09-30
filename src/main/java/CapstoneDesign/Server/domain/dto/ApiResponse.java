package CapstoneDesign.Server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus code;
    private String message;
    private Object data;
}
