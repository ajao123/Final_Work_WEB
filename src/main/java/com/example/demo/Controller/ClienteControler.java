package com.example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.demo.model.Cliente;
import com.example.demo.model.Prato;
import com.example.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteControler {

	@Autowired
	private ClienteService clienteService;
	
	//Chamar o método
	@GetMapping
	public ResponseEntity<Page<Cliente>> listar(Pageable pageable){
		return new ResponseEntity<>(clienteService.listarClientes(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloId(@PathVariable Long id){
		return new ResponseEntity<>(clienteService.buscarPeloId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente){
		return new ResponseEntity<>(clienteService.salvar(cliente), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
