// ClienteResponse.java
package com.tesis2025.tesis2025.cliente.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ClienteResponse(
    UUID idCliente,
    String nombre,
    String apellido,
    String correo,
    String numDoc,
    String celular,
    String direccion,
    OffsetDateTime creadoEn
) {}
