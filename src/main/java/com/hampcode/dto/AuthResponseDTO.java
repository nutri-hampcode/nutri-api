package com.hampcode.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;
    private String token;
    private String name;
    private String role;
}
