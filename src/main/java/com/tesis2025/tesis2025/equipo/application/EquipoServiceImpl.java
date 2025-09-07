package com.tesis2025.tesis2025.equipo.application;

import com.tesis2025.tesis2025.equipo.domain.Equipo;
import com.tesis2025.tesis2025.equipo.dto.*;
import com.tesis2025.tesis2025.equipo.repository.EquipoRepository;
import com.tesis2025.tesis2025.pedido.domain.Pedido;
import com.tesis2025.tesis2025.pedido.dto.CreatePedidoRequest;
import com.tesis2025.tesis2025.pedido.dto.PedidoResponse;
import com.tesis2025.tesis2025.pedido.dto.UpdatePedidoRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipoServiceImpl implements EquipoService {

        private final EquipoRepository repo;
         @Transactional(readOnly = true)
    @Override
    public Page<EquipoResponse> list(String q, Pageable pageable) {
        Page<Equipo> page;
        if (q == null || q.isBlank()) {
            page = repo.findAll(pageable);
        } else {
            page = repo
                .findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(
                    q, q, q, pageable
                );
        }
        return page.map(this::toResponse);
    }

    @Override
    @Transactional
    public EquipoResponse update(UUID id, UpdateEquipoRequest r) {
        var p = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado: " + id));

        if (r.nombre() != null) p.setNombre(trim(r.nombre()));
        // aplica más campos del request cuando los agregues

        var saved = repo.save(p);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public EquipoResponse get(UUID id) {
        var c = repo.findById(id).orElseThrow();
        return toResponse(c);
    }

    @Override
    public EquipoResponse create(CreateEquipoRequest r) {
        var c = Equipo.builder()
                .nombre(trim(r.nombre()))
                .servicio(trim(r.marca()))
                .evento(trim(r.modelo()))
                .cliente(r.cantidad())
                .build();
        return toResponse(repo.save(c));
    }



    private EquipoResponse toResponse(Equipo c) {
        return new EquipoResponse(
                c.getIdEquipo(), c.getNombre(), c.getMarca(),
                c.getModelo(), c.getCantidad()
        );
    }

    private static String trim(String s) { return s == null ? null : s.trim(); }

}