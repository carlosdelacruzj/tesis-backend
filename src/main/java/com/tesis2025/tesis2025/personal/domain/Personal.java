package com.tesis2025.tesis2025.personal.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;
@Entity
@Table(name = "personal")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class Personal {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id_personal")
    private UUID idPersonal;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false, length = 8)
    private String dni;

}