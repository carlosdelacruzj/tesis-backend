package com.tesis2025.tesis2025.cliente.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {

  @Id
  @GeneratedValue
  @JdbcTypeCode(SqlTypes.UUID)
  @Column(name = "id_cliente")
  private UUID idCliente;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false, length = 100)
  private String apellido;

  @Column(length = 254)
  private String correo;

  @Column(name = "num_doc", length = 32)
  private String numDoc;

  @Column(length = 32)
  private String celular;

  @Column(columnDefinition = "text")
  private String direccion;

  @Column(name = "creado_en", nullable = false)
  private OffsetDateTime creadoEn;

  @PrePersist
  void prePersist() { creadoEn = OffsetDateTime.now(); }
}
