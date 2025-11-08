package com.sabor.gourmet.service;

import com.sabor.gourmet.domain.Cliente;
import com.sabor.gourmet.repo.ClienteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepo clienteRepo;

    public ClienteService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepo.findById(id).orElse(null);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente existente = obtenerPorId(id);
        if (existente != null) {
            existente.setNombres(cliente.getNombres());
            existente.setApellidos(cliente.getApellidos());
            existente.setDni(cliente.getDni());
            existente.setTelefono(cliente.getTelefono());
            existente.setCorreo(cliente.getCorreo());
            existente.setActivo(cliente.isActivo());
            return clienteRepo.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        clienteRepo.deleteById(id);
    }
}
