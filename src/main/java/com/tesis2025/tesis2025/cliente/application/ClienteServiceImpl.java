// ClienteServiceImpl.java
package com.tesis2025.tesis2025.cliente.application;

import com.tesis2025.tesis2025.cliente.domain.Cliente;
import com.tesis2025.tesis2025.cliente.dto.*;
import com.tesis2025.tesis2025.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository repo;

  @Transactional(readOnly = true)
  @Override
  public Page<ClienteResponse> list(String q, Pageable pageable) {
    Page<Cliente> page;
    if (q == null || q.isBlank()) {
      page = repo.findAll(pageable);
    } else {
      // Busca por nombre, apellido o correo (si quieres, agrega doc/celular)
      page = repo.findAll(pageable)
                 .map(c -> c) // placeholder si luego añades query dedicada
                 .map(c -> c); // (puedes crear un método en repo si quieres filtro en DB)
    }
    return page.map(this::toResponse);
  }

  @Transactional(readOnly = true)
  @Override
  public ClienteResponse get(UUID id) {
    var c = repo.findById(id).orElseThrow();
    return toResponse(c);
  }

  @Override
  public ClienteResponse create(CreateClienteRequest r) {
    var c = Cliente.builder()
        .nombre(trim(r.nombre()))
        .apellido(trim(r.apellido()))
        .correo(normalizeEmail(r.correo()))
        .numDoc(trim(r.numDoc()))
        .celular(trim(r.celular()))
        .direccion(r.direccion())
        .build();
    return toResponse(repo.save(c));
  }

  @Override
  public ClienteResponse update(UUID id, UpdateClienteRequest r) {
    var c = repo.findById(id).orElseThrow();
    if (r.correo()   != null) c.setCorreo(normalizeEmail(r.correo()));
    if (r.celular()  != null) c.setCelular(trim(r.celular()));
    if (r.direccion()!= null) c.setDireccion(r.direccion());
    return toResponse(c);
  }

  @Override
  public ClienteResponse updateLegacy(UpdateClienteLegacy r) {
    return update(r.idCliente(), new UpdateClienteRequest(r.correo(), r.celular(), null));
  }

  @Override
  public void delete(UUID id) {
    repo.deleteById(id);
  }

  // ---------- mapping ----------
  private ClienteResponse toResponse(Cliente c) {
    return new ClienteResponse(
        c.getIdCliente(), c.getNombre(), c.getApellido(), c.getCorreo(),
        c.getNumDoc(), c.getCelular(), c.getDireccion(), c.getCreadoEn()
    );
  }
  private static String normalizeEmail(String e) { return e == null ? null : e.trim().toLowerCase(); }
  private static String trim(String s) { return s == null ? null : s.trim(); }
}
