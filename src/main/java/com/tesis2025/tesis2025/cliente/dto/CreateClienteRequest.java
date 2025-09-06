// CreateClienteRequest.java
package com.tesis2025.tesis2025.cliente.dto;

import jakarta.validation.constraints.*;

public record CreateClienteRequest(
    @NotBlank @Size(max = 100) String nombre,
    @NotBlank @Size(max = 100) String apellido,
    @Email @Size(max = 254) String correo,
    @Size(max = 32) String numDoc,
    @Size(max = 32) String celular,
    String direccion
) {}
