package com.tesis2025.tesis2025.personal.dto;

import jakarta.validation.constraints.*;

public record CreatePersonalRequest(
    @NotBlank @Size(max = 100) String nombre,
    @NotBlank @Size(max = 100) String cargo,
    @NotBlank @Size(max = 8) String dni
) {}


    
