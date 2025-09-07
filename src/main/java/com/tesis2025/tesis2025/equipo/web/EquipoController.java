package com.tesis2025.tesis2025.equipo.web;

import com.tesis2025.tesis2025.equipo.application.EquipoService;
import com.tesis2025.tesis2025.equipo.dto.*;
import com.tesis2025.tesis2025.equipo.dto.CreateEquipoRequest;
import com.tesis2025.tesis2025.equipo.dto.EquipoResponse;
import com.tesis2025.tesis2025.equipo.dto.UpdateEquipoRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;
    @GetMapping("/api/equipos")
    public ResponseEntity<Page<EquipoResponse>> list(
    @RequestParam(required = false) String q,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "25") int size,
    @RequestParam(defaultValue = "creadoEn,desc") String sort) {

    Sort s = Sort.by(sort.split(",")[0]).ascending();
    if (sort.endsWith(",desc")) s = s.descending();
    return ResponseEntity.ok(service.list(q, PageRequest.of(page, size, s)));
  }

  
  @GetMapping("/api/equipos/{id}")
  public ResponseEntity<EquipoResponse> get(@PathVariable UUID id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping("/api/equipos")
  public ResponseEntity<EquipoResponse> create(@Valid @RequestBody CreateEquipoRequest req) {
    var created = service.create(req);
    return ResponseEntity.created(URI.create("/api/equipos/" + created.idEquipo())).body(created);
  }

  @PutMapping("/api/equipos/{id}")
  public ResponseEntity<EquipoResponse> update(@PathVariable UUID id,
                                               @Valid @RequestBody UpdateEquipoRequest req) {
    return ResponseEntity.ok(service.update(id, req));
  }


    @GetMapping("/equipo/consulta/getAllequipo")
    public List<EquipoResponse> legacyGetAll() {
    return service.list(null, Pageable.unpaged()).getContent();
  }

  @PostMapping("/equipo/registro/postequipo")
  public Map<String, String> legacyCreate(@Valid @RequestBody CreateEquipoRequest req) {
    service.create(req);
    return Map.of("Status", "Registro exitoso");
  }

  //@PutMapping("/equipo/actualiza/putequipoById")
  //public Map<String, String> legacyUpdate(@Valid @RequestBody UpdateequipoLegacy req) {
  //service.updateLegacy(req);
  //return Map.of("Status", "Actualizacion exitosa");
  //}

}

    