package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Prato;

public interface PratoService {
	Prato salvar(Prato prato);
	void remover(Long id);
	Prato buscarPratoPeloId(Long id);
	Page<Prato> listarPratos(Pageable pageable);

}