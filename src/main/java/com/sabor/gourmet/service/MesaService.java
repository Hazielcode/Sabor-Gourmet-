package com.sabor.gourmet.service;

import com.sabor.gourmet.domain.Mesa;
import com.sabor.gourmet.repo.MesaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepo mesaRepo;

    public MesaService(MesaRepo mesaRepo) {
        this.mesaRepo = mesaRepo;
    }

    public List<Mesa> listar() {
        return mesaRepo.findAll();
    }

    public Mesa guardar(Mesa mesa) {
        return mesaRepo.save(mesa);
    }

    public Mesa actualizar(Long id, Mesa mesa) {
        Mesa existente = mesaRepo.findById(id).orElse(null);
        if (existente != null) {
            existente.setNumero(mesa.getNumero());
            existente.setCapacidad(mesa.getCapacidad());
            existente.setEstado(mesa.getEstado());
            return mesaRepo.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        mesaRepo.deleteById(id);
    }
}
