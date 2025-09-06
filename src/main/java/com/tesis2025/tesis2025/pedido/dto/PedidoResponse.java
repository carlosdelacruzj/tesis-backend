package com.tesis2025.tesis2025.pedido.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PedidoResponse (
    UUID idPedido,
    String nombre,
    OffsetDateTime creadoEn,
    String servicio,
    String evento,
    String cliente
    //boolean estado
    
) {}
