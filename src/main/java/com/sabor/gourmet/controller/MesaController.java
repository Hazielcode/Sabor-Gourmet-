package com.sabor.gourmet.controller;

import com.sabor.gourmet.domain.Mesa;
import com.sabor.gourmet.service.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public List<Mesa> listar() {
        return mesaService.listar();
    }

    @PostMapping
    public Mesa crear(@RequestBody Mesa mesa) {
        return mesaService.guardar(mesa);
    }

    @PutMapping("/{id}")
    public Mesa actualizar(@PathVariable Long id, @RequestBody Mesa mesa) {
        return mesaService.actualizar(id, mesa);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
    }
}
