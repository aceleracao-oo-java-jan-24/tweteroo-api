package com.tweteroo.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Field avatar is mandatory")
    private String avatar;

    @NotBlank(message = "Field username is mandatory")
    @Size(max = 100, message = "Maximum size is 100 characters")
    private String username;
}
