package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

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
	public Optional<Cliente> buscarPeloId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente atualizarCliente(Cliente cliente, Long id) {
		Optional<Cliente> clienteSave = clienteRepository.findById(id);
    	if(!clienteSave.isPresent()) {
    		throw new EmptyResultDataAccessException(1);
    	}
    	BeanUtils.copyProperties(cliente, clienteSave.get(), "id");
    	return clienteRepository.save(clienteSave.get());
	}
}
