package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cliente;
import com.example.demo.model.Prato;
import com.example.demo.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/cliente/registro")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("RegistroCliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@RequestMapping("/cliente/salvar")
	public ModelAndView salvarCliente(Cliente cliente) {
		clienteService.salvar(cliente);
		return listarPratos();
	}
	
	@RequestMapping("/cliente/listar")
	public ModelAndView listarPratos() {
		List<Cliente> clientes = clienteService.listarClientes();
		ModelAndView mv = new ModelAndView("ListagemClientes");
		mv.addObject("listaDeClientes", clientes);
		return mv;
	}
	
	@RequestMapping("cliente/excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id) {
		clienteService.remover(id);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
		return mv;
	}
	
	@RequestMapping("cliente/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPeloId(id).get();
		ModelAndView mv = new ModelAndView("RegistroCliente");
		mv.addObject("cliente", cliente);
		return mv;
	}
}
