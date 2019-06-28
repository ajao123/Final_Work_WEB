package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Prato;

public interface PratoService {
	Prato salvar(Prato prato, MultipartFile image);
	void remover(Long id);
	Optional<Prato> buscarPratoPeloId(Long id);
	List<Prato> listarPratos();
	Prato atualizarPrato(Prato prato, Long id);

}