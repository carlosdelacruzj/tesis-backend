// UpdateClienteRequest.java   (REST moderno: el id va en la ruta)
package com.tesis2025.tesis2025.cliente.dto;

import jakarta.validation.constraints.*;

public record UpdateClienteRequest(
    @Email @Size(max = 254) String correo,
    @Size(max = 32) String celular,
    String direccion
) {}
