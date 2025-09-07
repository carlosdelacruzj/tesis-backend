package com.tesis2025.tesis2025.personal.dto;

import jakarta.validation.constraints.*;

public record UpdatePersonalRequest (
    @Size(max = 100) String nombre,
    @Size(max = 100) String cargo
){}