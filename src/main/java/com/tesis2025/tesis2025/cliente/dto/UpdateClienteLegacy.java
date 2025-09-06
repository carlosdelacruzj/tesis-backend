// UpdateClienteLegacy.java    (compat con tu endpoint antiguo que envía el id en el body)
package com.tesis2025.tesis2025.cliente.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record UpdateClienteLegacy(
    @NotNull UUID idCliente,
    @Email @Size(max = 254) String correo,
    @Size(max = 32) String celular
) {}
