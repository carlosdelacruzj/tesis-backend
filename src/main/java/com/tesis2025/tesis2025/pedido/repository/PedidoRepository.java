package com.tesis2025.tesis2025.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tesis2025.tesis2025.pedido.domain.Pedido;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    //Optional<Pedido> findby---
    List<Pedido> getAllPedidos();
}
