package com.tesis2025.tesis2025.pedido.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class Pedido {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id_pedido")
    private UUID idPedido;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name =  "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(nullable = false, length = 100)
    private String servicio;   

    @Column(nullable = false, length = 100)
    private String evento;   

    @Column(nullable = false, length = 100)
    private String cliente;   

}


//id, nombre, fecha, servicio, evento, cliente, estado