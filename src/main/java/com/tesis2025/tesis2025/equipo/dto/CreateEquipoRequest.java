package com.tesis2025.tesis2025.equipo.dto;

import jakarta.validation.constraints.*;

public record CreatePedidoRequest(
    @NotBlank @Size(max = 100) String marca,
    @NotBlank @Size(max = 100) String modelo,
    @Positive Integer cantidad,
) {}