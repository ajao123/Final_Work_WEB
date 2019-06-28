package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Cliente;

public interface ClienteService {
	Cliente salvar(Cliente cliente);
	void remover(Long id);
	Optional<Cliente> buscarPeloId(Long id);
	List<Cliente> listarClientes(); 
	Cliente atualizarCliente(Cliente cliente, Long id);
}
