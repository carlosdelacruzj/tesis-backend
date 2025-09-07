package com.tesis2025.tesis2025.equipo.application;

import com.tesis2025.tesis2025.equipo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EquipoService {
    Page<EquipoResponse> list(String q, Pageable pageable);
    EquipoResponse get (UUID id);
    EquipoResponse create(CreateEquipoRequest r);
    EquipoResponse update(UUID id, UpdateEquipoRequest req);
}