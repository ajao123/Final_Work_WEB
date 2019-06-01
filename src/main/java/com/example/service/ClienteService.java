package com.example.service;

import com.example.demo.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
	Cliente salvar(Cliente cliente);
	void remover(Long id);
	Cliente buscarPeloId(Long id);
	Page<Cliente> listarClientes(Pageable pageable); 
}
