package com.tesis2025.tesis2025.pedido.repository;

import com.tesis2025.tesis2025.pedido.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    // Para la búsqueda por "q" (nombre/servicio/evento/cliente)
    Page<Pedido> findByNombreContainingIgnoreCaseOrServicioContainingIgnoreCaseOrEventoContainingIgnoreCaseOrClienteContainingIgnoreCase(
            String nombre, String servicio, String evento, String cliente, Pageable pageable
    );
}
