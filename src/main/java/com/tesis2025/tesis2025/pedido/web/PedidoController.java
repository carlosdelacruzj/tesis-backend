package com.tesis2025.tesis2025.pedido.web;

import com.tesis2025.tesis2025.cliente.dto.ClienteResponse;
import com.tesis2025.tesis2025.cliente.dto.CreateClienteRequest;
import com.tesis2025.tesis2025.cliente.dto.UpdateClienteLegacy;
import com.tesis2025.tesis2025.cliente.dto.UpdateClienteRequest;
import com.tesis2025.tesis2025.pedido.application.PedidoService;
import com.tesis2025.tesis2025.pedido.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor

public class PedidoController {
    
    private final PedidoService service;

    // ====== REST moderno ======

    @GetMapping("/api/pedidos")
    public ResponseEntity<Page<PedidoResponse>> list(
    @RequestParam(required = false) String q,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "25") int size,
    @RequestParam(defaultValue = "creadoEn,desc") String sort) {

    Sort s = Sort.by(sort.split(",")[0]).ascending();
    if (sort.endsWith(",desc")) s = s.descending();
    return ResponseEntity.ok(service.list(q, PageRequest.of(page, size, s)));
  }

  
  @GetMapping("/api/pedidos/{id}")
  public ResponseEntity<PedidoResponse> get(@PathVariable UUID id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping("/api/pedidos")
  public ResponseEntity<PedidoResponse> create(@Valid @RequestBody CreatePedidoRequest req) {
    var created = service.create(req);
    return ResponseEntity.created(URI.create("/api/pedidos/" + created.idPedido())).body(created);
  }

  @PutMapping("/api/pedidos/{id}")
  public ResponseEntity<PedidoResponse> update(@PathVariable UUID id,
                                               @Valid @RequestBody UpdatePedidoRequest req) {
    return ResponseEntity.ok(service.update(id, req));
  }


    @GetMapping("/pedido/consulta/getAllPedido")
    public List<PedidoResponse> legacyGetAll() {
    return service.list(null, Pageable.unpaged()).getContent();
  }

  @PostMapping("/pedido/registro/postPedido")
  public Map<String, String> legacyCreate(@Valid @RequestBody CreatePedidoRequest req) {
    service.create(req);
    return Map.of("Status", "Registro exitoso");
  }

  //@PutMapping("/pedido/actualiza/putPedidoById")
  //public Map<String, String> legacyUpdate(@Valid @RequestBody UpdatePedidoLegacy req) {
  //service.updateLegacy(req);
  //return Map.of("Status", "Actualizacion exitosa");
  //}

}
