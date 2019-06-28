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

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteControler {

	@Autowired
	private ClienteService clienteService;
	
	//Chamar o método
	@GetMapping
	public List<Cliente> listar(Pageable pageable){
		return clienteService.listarClientes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloId(@PathVariable Long id){
		return new ResponseEntity<>(clienteService.buscarPeloId(id).get(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> put(@RequestBody Cliente cliente, @PathVariable Long id){
		return new ResponseEntity<>(clienteService.atualizarCliente(cliente, id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente){
		return new ResponseEntity<>(clienteService.salvar(cliente), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.remover(id);
	}
	
}
