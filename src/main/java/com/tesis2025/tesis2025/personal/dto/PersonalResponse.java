package com.tesis2025.tesis2025.personal.dto;

import java.util.UUID;;

public record PersonalResponse (
    UUID idPersonal,
    String nombre,
    String cargo,
    String dni
){}