package com.tesis2025.tesis2025.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tesis2025.tesis2025.cliente.domain.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
  Optional<Cliente> findByNumDoc(String numDoc);
  List<Cliente> findAllByOrderByCreadoEnDesc();
}
