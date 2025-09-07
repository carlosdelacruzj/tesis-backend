package com.tesis2025.tesis2025.equipo.dto;

import jakarta.validation.constraints.*;

public record UpdateEquipoRequest(
    @Size(max = 100) String nombre,
    @Size(max = 100) String marca,
    @Size(max = 100) String modelo,
    Integer cantidad
) {}
