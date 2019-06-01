package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Prato;
import com.example.demo.repository.PratoRepository;
import com.example.service.PratoService;

@Service
public class PratoServiceImpl implements PratoService{

	@Autowired
	private PratoRepository pratoRepository;
	
	@Override
	public Prato salvar(Prato prato) {
		return pratoRepository.save(prato);
	}

	@Override
	public void remover(Long id) {
		pratoRepository.deleteById(id);
	}

	@Override
	public Prato buscarPratoPeloId(Long id) {
		return pratoRepository.getOne(id);
	}

	@Override
	public Page<Prato> listarPratos(Pageable pageable) {
		return new PageImpl<>(pratoRepository.findAll(), pageable, pageable.getPageSize());
	}

}
