package com.sabor.gourmet.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private int capacidad;

    @Enumerated(EnumType.STRING)
    private EstadoMesa estado;

    public enum EstadoMesa {
        DISPONIBLE,
        RESERVADA,
        MANTENIMIENTO,
        OCUPADA;

        // ✅ Permite recibir valores sin importar mayúsculas/minúsculas
        @JsonCreator
        public static EstadoMesa fromString(String value) {
            if (value == null) return null;
            try {
                return EstadoMesa.valueOf(value.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Estado inválido: " + value +
                        ". Valores válidos: DISPONIBLE, RESERVADA, MANTENIMIENTO, OCUPADA");
            }
        }

        // ✅ Devuelve texto capitalizado para mostrar bonito en JSON si se desea
        @JsonValue
        public String toJson() {
            String lower = this.name().toLowerCase();
            return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
        }
    }
}
