package com.tesis2025.tesis2025.equipo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name="equipo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipo {
    
    @Id
    @GeneratedValue
    @JdbcTypeCode (SqlTypes.UUID)
    @Column(name="id_equipo")
    private UUID idEquipo;

    @Column(nullable=false, length=100)
    private String nombre;

    @Column(nullable=false, length=100)
    private String marca;

    @Column(nullable=false, length=100)
    private String modelo;

    @Column(nullable=false)
    private Integer cantidad;

}
