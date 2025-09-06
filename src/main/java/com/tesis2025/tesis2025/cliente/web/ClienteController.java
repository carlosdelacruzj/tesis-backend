package com.tesis2025.tesis2025.cliente.web;

import com.tesis2025.tesis2025.cliente.application.ClienteService;
import com.tesis2025.tesis2025.cliente.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService service;

  // ====== REST moderno ======

  @GetMapping("/api/clientes")
  public ResponseEntity<Page<ClienteResponse>> list(
      @RequestParam(required = false) String q,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "25") int size,
      @RequestParam(defaultValue = "creadoEn,desc") String sort) {

    Sort s = Sort.by(sort.split(",")[0]).ascending();
    if (sort.endsWith(",desc")) s = s.descending();
    return ResponseEntity.ok(service.list(q, PageRequest.of(page, size, s)));
  }

  @GetMapping("/api/clientes/{id}")
  public ResponseEntity<ClienteResponse> get(@PathVariable UUID id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping("/api/clientes")
  public ResponseEntity<ClienteResponse> create(@Valid @RequestBody CreateClienteRequest req) {
    var created = service.create(req);
    return ResponseEntity.created(URI.create("/api/clientes/" + created.idCliente())).body(created);
  }

  @PutMapping("/api/clientes/{id}")
  public ResponseEntity<ClienteResponse> update(@PathVariable UUID id,
                                                @Valid @RequestBody UpdateClienteRequest req) {
    return ResponseEntity.ok(service.update(id, req));
  }

  @DeleteMapping("/api/clientes/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  // ====== Alias LEGACY (compat con tu front actual) ======

  @GetMapping("/cliente/consulta/getDataCliente/{doc}")
  public List<ClienteResponse> legacyGetByDoc(@PathVariable("doc") String doc) {
    // reusa el service; si necesitas exacto por doc, añade un método específico
    // de momento, devolvemos 0..1 si existe
    return service.list(null, Pageable.unpaged())
                  .stream()
                  .filter(c -> Objects.equals(c.numDoc(), doc))
                  .toList();
  }

  @GetMapping("/cliente/consulta/getAllCliente")
  public List<ClienteResponse> legacyGetAll() {
    return service.list(null, Pageable.unpaged()).getContent();
  }

  @GetMapping("/cliente/consulta/getByIdCliente/{id}")
  public ClienteResponse legacyGetById(@PathVariable("id") UUID id) {
    return service.get(id);
  }

  @PostMapping("/cliente/registro/postCliente")
  public Map<String, String> legacyCreate(@Valid @RequestBody CreateClienteRequest req) {
    service.create(req);
    return Map.of("Status", "Registro exitoso");
  }

  @PutMapping("/cliente/actualiza/putClienteById")
  public Map<String, String> legacyUpdate(@Valid @RequestBody UpdateClienteLegacy req) {
    service.updateLegacy(req);
    return Map.of("Status", "Actualizacion exitosa");
  }
}
