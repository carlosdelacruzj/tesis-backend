package com.tesis2025.tesis2025.personal.repository;

import com.tesis2025.tesis2025.personal.domain.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonalRepository extends JpaRepository<Personal, UUID>{

    Page<Personal> findByNombreContainingIgnoreCaseOrCargoContainingIgnoreCaseOrDniContainingIgnoreCase(
            String nombre,
            String cargo,
            String dni,
            Pageable pageable
    );
}