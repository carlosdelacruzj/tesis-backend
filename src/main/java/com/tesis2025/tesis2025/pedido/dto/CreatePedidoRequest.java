// CreateClienteRequest.java
package com.tesis2025.tesis2025.pedido.dto;

import jakarta.validation.constraints.*;

public record CreatePedidoRequest(
    @NotBlank @Size(max = 100) String nombre,
    @NotBlank @Size(max = 100) String servicio,
    @NotBlank @Size(max = 254) String evento,
    @NotBlank @Size(max = 254) String cliente,
    String direccion
) {}
