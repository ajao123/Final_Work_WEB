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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Prato;
import com.example.demo.service.PratoService;
import com.fasterxml.jackson.annotation.JsonView;



@RestController
@RequestMapping("/pratos")
public class PratoControler {

	@Autowired
	private PratoService pratoService;
	
	//Chamar o método
	@GetMapping
	public List<Prato> listar(){
		return pratoService.listarPratos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Prato> buscarPeloId(@PathVariable Long id){
		return new ResponseEntity<>(pratoService.buscarPratoPeloId(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Prato> salvar(@Valid @RequestBody Prato prato){
		return new ResponseEntity<>(pratoService.salvar(prato, null), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Prato> put(@RequestBody Prato prato,@PathVariable Long id){
		return new ResponseEntity<>(pratoService.atualizarPrato(prato, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pratoService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
