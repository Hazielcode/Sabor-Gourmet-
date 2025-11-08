package com.sabor.gourmet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Acción realizada (CREAR, EDITAR, ELIMINAR)
    private String accion;

    // Nombre de la entidad afectada (Cliente, Mesa, etc.)
    private String entidad;

    // ID de la entidad modificada (opcional pero muy útil)
    private Long entidadId;

    // Usuario responsable (si luego conectas con autenticación)
    private String usuario;

    // Fecha y hora exacta del evento
    private LocalDateTime fechaHora = LocalDateTime.now();
}
