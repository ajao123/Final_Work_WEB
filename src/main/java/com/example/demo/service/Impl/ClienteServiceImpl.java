package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void remover(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente buscarPeloId(Long id) {
		return clienteRepository.getOne(id);
	}

	@Override
	public Page<Cliente> listarClientes(Pageable pageable) {
		return new PageImpl<>(clienteRepository.findAll(), pageable, pageable.getPageSize());
	}

}
