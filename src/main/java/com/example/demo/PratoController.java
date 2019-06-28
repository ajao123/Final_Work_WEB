package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Prato;
import com.example.demo.service.PratoService;

@Controller
public class PratoController {

	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/prato/registro")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("RegistroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@RequestMapping("/prato/salvar")
	public String salvarPrato(Prato prato, @RequestParam(value="imagem") MultipartFile imagem) {
		pratoService.salvar(prato, imagem);
		return "PratoAdicionadoSucesso";
	}
	
	@RequestMapping("/prato/listar")
	public ModelAndView listarPratos() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("ListagemPratos");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}
	
	@RequestMapping("/prato/cardapio")
	public ModelAndView cardapio() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("Pratos");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}
	
	@RequestMapping("prato/excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id) {
		pratoService.remover(id);
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");
		return mv;
	}
	
	@RequestMapping("prato/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable Long id) {
		Prato prato = pratoService.buscarPratoPeloId(id).get();
		ModelAndView mv = new ModelAndView("RegistroPrato");
		mv.addObject("prato", prato);
		return mv;
	}
	
}
