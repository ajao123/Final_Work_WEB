package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Prato;
import com.example.service.PratoService;


@RestController
@RequestMapping("/pratos")
public class PratoController {

	@Autowired
	private PratoService pratoService;
	
	//Chamar o método
	@GetMapping
	public ResponseEntity<Page<Prato>> listar(Pageable pageable){
		return new ResponseEntity<>(pratoService.listarPratos(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Prato> buscarPeloId(@PathVariable Long id){
		return new ResponseEntity<>(pratoService.buscarPratoPeloId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Prato> salvar(@Valid @RequestBody Prato prato){
		return new ResponseEntity<>(pratoService.salvar(prato), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pratoService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
