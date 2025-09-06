package com.tesis2025.tesis2025.cliente.web;

import com.tesis2025.tesis2025.cliente.application.ClienteService;
import com.tesis2025.tesis2025.cliente.dto.ClienteResponse;
import com.tesis2025.tesis2025.cliente.dto.CreateClienteRequest;
import com.tesis2025.tesis2025.cliente.dto.UpdateClienteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService service;

  @GetMapping
  public ResponseEntity<Page<ClienteResponse>> list(
      @RequestParam(required = false) String q,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "25") int size,
      @RequestParam(defaultValue = "creadoEn,desc") String sort) {

    // Parseo seguro del sort: "prop,asc|desc"
    String[] parts = sort.split(",", 2);
    String prop = parts[0];
    boolean desc = parts.length > 1 && "desc".equalsIgnoreCase(parts[1]);

    Sort s = Sort.by(prop);
    s = desc ? s.descending() : s.ascending();

    PageRequest pr = PageRequest.of(page, size, s);
    return ResponseEntity.ok(service.list(q, pr));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> get(@PathVariable UUID id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping
  public ResponseEntity<ClienteResponse> create(@Valid @RequestBody CreateClienteRequest req) {
    var created = service.create(req);

    // Construye Location con la URL de la petición actual + /{id}
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.idCliente())
        .toUri();

    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> update(@PathVariable UUID id,
                                                @Valid @RequestBody UpdateClienteRequest req) {
    return ResponseEntity.ok(service.update(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
