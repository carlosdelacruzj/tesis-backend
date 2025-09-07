package com.tesis2025.tesis2025.equipo.dto;


import java.util.UUID;

public record EquipoResponse (
    UUID idEquipo,
    String nombre,
    String marca,
    String modelo,
    Integer cantidad
    
) {}
