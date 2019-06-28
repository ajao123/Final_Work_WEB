package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Prato;
import com.example.demo.repository.PratoRepository;
import com.example.demo.service.PratoService;
import com.example.demo.util.AulaFileUtils;


@Service
public class PratoServiceImpl implements PratoService{

	@Autowired
	private PratoRepository pratoRepository;
	
	@Override
	public Prato salvar(Prato prato, MultipartFile image) {
		String caminho = "images/"+prato.getName()+".jpg";
		AulaFileUtils.salvarImagem(caminho, image);
		return pratoRepository.save(prato);
	}

	@Override
	public void remover(Long id) {
		pratoRepository.deleteById(id);
	}

	@Override
	public Optional<Prato> buscarPratoPeloId(Long id) {
		return pratoRepository.findById(id);
	}

	@Override
	public List<Prato> listarPratos() {
		return pratoRepository.findAll();
	}

	@Override
	public Prato atualizarPrato(Prato prato, Long id) {
		Optional<Prato> pratoSave = pratoRepository.findById(id);
    	if(!pratoSave.isPresent()) {
    		throw new EmptyResultDataAccessException(1);
    	}
    	BeanUtils.copyProperties(prato, pratoSave.get(), "id");
    	return pratoRepository.save(pratoSave.get());
	}

}
