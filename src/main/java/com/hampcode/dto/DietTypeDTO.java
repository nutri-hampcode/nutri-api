package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DietTypeDTO {

    private Integer id;

    @NotBlank(message = "Diet Type cannot be blank!!")
    @Size(max = 40, message = "Diet Type can only have a maximum of 40 characters")
    private String type;

    @NotBlank(message = "Diet type description must be provided..")
    @Size(max = 255, message = "Diet Type description can only have a maximum of 255 characters")
    private String description;
}