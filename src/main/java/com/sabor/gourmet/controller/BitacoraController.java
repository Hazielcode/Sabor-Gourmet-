package com.sabor.gourmet.controller;

import com.sabor.gourmet.domain.Bitacora;
import com.sabor.gourmet.repo.BitacoraRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bitacora")
@CrossOrigin(origins = "*") // Permite que el front (HTML/JS) acceda sin problema
public class BitacoraController {

    private final BitacoraRepo bitacoraRepo;

    public BitacoraController(BitacoraRepo bitacoraRepo) {
        this.bitacoraRepo = bitacoraRepo;
    }

    /**
     * 🔹 Devuelve todos los registros de la bitácora en orden descendente (más recientes primero)
     */
    @GetMapping
    public List<Bitacora> listarBitacora() {
        return bitacoraRepo.findAll()
                .stream()
                .sorted((a, b) -> b.getFechaHora().compareTo(a.getFechaHora()))
                .toList();
    }
}
