// ClienteService.java
package com.tesis2025.tesis2025.cliente.application;

import com.tesis2025.tesis2025.cliente.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClienteService {
  Page<ClienteResponse> list(String q, Pageable pageable);
  ClienteResponse get(UUID id);
  ClienteResponse create(CreateClienteRequest req);
  ClienteResponse update(UUID id, UpdateClienteRequest req);
  ClienteResponse updateLegacy(UpdateClienteLegacy req); // compat
  void delete(UUID id);
}
