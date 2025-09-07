package com.tesis2025.tesis2025.equipo.repository;

import com.tesis2025.tesis2025.equipo.domain.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipoRepository extends JpaRepository<Equipo, UUID> {

    // 🔎 Búsqueda por "q" en los campos principales (nombre, marca, modelo)
    Page<Equipo> findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(
            String nombre,
            String marca,
            String modelo,
            Pageable pageable
    );
}