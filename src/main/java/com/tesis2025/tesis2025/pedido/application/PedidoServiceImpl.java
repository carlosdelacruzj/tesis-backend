package com.tesis2025.tesis2025.pedido.application;

import com.tesis2025.tesis2025.pedido.domain.Pedido;
import com.tesis2025.tesis2025.pedido.dto.*;
import com.tesis2025.tesis2025.pedido.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repo;

    @Transactional(readOnly = true)
    @Override
    public Page<PedidoResponse> list(String q, Pageable pageable) {
        Page<Pedido> page;
        if (q == null || q.isBlank()) {
            page = repo.findAll(pageable);
        } else {
            page = repo
                .findByNombreContainingIgnoreCaseOrServicioContainingIgnoreCaseOrEventoContainingIgnoreCaseOrClienteContainingIgnoreCase(
                    q, q, q, q, pageable
                );
        }
        return page.map(this::toResponse);
    }

    @Override
    @Transactional
    public PedidoResponse update(UUID id, UpdatePedidoRequest r) {
        var p = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));

        if (r.nombre() != null) p.setNombre(trim(r.nombre()));
        // aplica más campos del request cuando los agregues

        var saved = repo.save(p);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public PedidoResponse get(UUID id) {
        var c = repo.findById(id).orElseThrow();
        return toResponse(c);
    }

    @Override
    public PedidoResponse create(CreatePedidoRequest r) {
        var c = Pedido.builder()
                .nombre(trim(r.nombre()))
                .servicio(trim(r.servicio()))
                .evento(trim(r.evento()))
                .cliente(trim(r.cliente()))
                .build();
        return toResponse(repo.save(c));
    }

    private PedidoResponse toResponse(Pedido c) {
        return new PedidoResponse(
                c.getIdPedido(), c.getNombre(), c.getCreadoEn(),
                c.getServicio(), c.getEvento(), c.getCliente()
        );
    }

    private static String trim(String s) { return s == null ? null : s.trim(); }
}
