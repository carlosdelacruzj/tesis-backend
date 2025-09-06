//PedidoService.java
package com.tesis2025.tesis2025.pedido.application;

import com.tesis2025.tesis2025.pedido.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PedidoService {
    Page<PedidoResponse> list(String q, Pageable pageable);
    PedidoResponse get(UUID id);
    PedidoResponse create(CreatePedidoRequest r);
    PedidoResponse update(UUID id, UpdatePedidoRequest req);
}
