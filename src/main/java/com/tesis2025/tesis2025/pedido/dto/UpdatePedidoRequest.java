package com.tesis2025.tesis2025.pedido.dto;

import jakarta.validation.constraints.*;

public record UpdatePedidoRequest(
    @Size(max = 100) String nombre,
    String cliente
) {}
