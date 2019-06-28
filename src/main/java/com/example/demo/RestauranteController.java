package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cliente;
import com.example.demo.model.Compra;
import com.example.demo.model.Gerente;
import com.example.demo.model.Pedido;
import com.example.demo.model.Prato;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.CompraRepository;
import com.example.demo.repository.GerenteRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PratoService;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PratoService pratoService;
	
	@Autowired
	private GerenteRepository gerenteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@RequestMapping("/inicial")
	public String paginaInicial() {
		return "PaginaInicial";
	}
	
	@RequestMapping("/visitante")
	public ModelAndView paginaVisitante() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("PaginaVisitante");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}
	
	@RequestMapping("/cliente")
	public String paginaCliente() {
		return "PaginaCliente";
	}
	
	@RequestMapping("/gerente")
	public String paginaGerente() {
		return "PaginaGerente";
	}
	
	@RequestMapping("/gerente/login")
	public String loginGerente(Gerente gerente) {
		Optional<Gerente> gerenteOficial = gerenteRepository.findById((long) 1);
		String login = gerenteOficial.get().getLogin();
		String senha = gerenteOficial.get().getSenha();

		if(gerente.getLogin().equalsIgnoreCase(login) && 
		   gerente.getSenha().equalsIgnoreCase(senha)){
			return "LoginGerenteSucesso";
		}else {
			return "LoginGerenteFail";
		}		
	}
	
	
	@RequestMapping("/gerente/telaprincipal")
	public String telaPrincipal() {
		return "TelaPrincipalGerente";
	}
	
	
	@RequestMapping("/gerente/registroprato")
	public ModelAndView registroPrato() {
		ModelAndView mv = new ModelAndView("RegistroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@RequestMapping("/gerente/salvarprato")
	public String salvarPrato(Prato prato, @RequestParam(value="imagem") MultipartFile imagem) {
		pratoService.salvar(prato, imagem);
		return "PratoAdicionadoSucesso";
	}
	
	@RequestMapping("/gerente/listarpratos")
	public ModelAndView listarPratos() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("ListagemPratos");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}
	
	@RequestMapping("/cliente/registro")
	public ModelAndView registroCliente() {
		ModelAndView mv = new ModelAndView("RegistroCliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@RequestMapping("/cliente/salvarcliente")
	public String salvarCliente(Cliente cliente) {
		clienteService.salvar(cliente);
		return "ClienteAdicionadoSucesso";
	}
	
	@RequestMapping("/cliente/login")
	public ModelAndView loginCliente(Cliente cliente) {
		List<Cliente> clientes = clienteRepository.findCliente(cliente.getEmail(), cliente.getSenha());
		List<Prato> pratos = pratoService.listarPratos();
		List<Pedido> pedidosDesejados = new ArrayList<Pedido>();
		
		pedidoRepository.deletePedidos(clientes.get(0).getId());
		Compra compra = compraRepository.save(new Compra(cliente.getId(), "", 0.0));
		for(Prato prato : pratos) {
			pedidoRepository.save(
				new Pedido(clientes.get(0).getId(), prato.getId(), compra.getId(), prato.getName(), prato.getPrice(), false));
		}
		
		if(clientes.isEmpty()) {
			ModelAndView mv = new ModelAndView("LoginClienteFail");
			return mv;
		}
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<Compra> compras = Arrays.asList(compra);
		ModelAndView mv = new ModelAndView("TelaPrincipalCliente");
		mv.addObject("listaDeClientes", clientes);
		mv.addObject("listaDePedidos", pedidos);
		mv.addObject("listaDePedidosDesejados", pedidosDesejados);
		mv.addObject("listaDeCompras", compras);
		return mv;
	}
	
	
	@RequestMapping("/cliente/login/rmpedido/{id}")
	public ModelAndView pedidoIndesejado(@PathVariable Long id){
		
		Pedido pedido = pedidoRepository.findById(id).get();
		pedido.setSelecionado(false);
		pedidoRepository.save(pedido);
		
		Compra compra = compraRepository.findById(pedido.getIdCompra()).get();
		compra.setResult(compra.getResult()-pedido.getPrice());
		compraRepository.save(compra);
		
		Cliente cliente = clienteRepository.findById(pedido.getIdcliente()).get();
		List<Cliente> clientes = Arrays.asList(cliente);

		List<Pedido> pedidosDesejados = new ArrayList<Pedido>();
	
		List<Pedido> pedidos = pedidoRepository.findAll();
		for(Pedido p : pedidos) {
			if(p.getSelecionado()) {
				pedidosDesejados.add(p);
			}
		}
		
		if(clientes.isEmpty()) {
			ModelAndView mv = new ModelAndView("LoginClienteFail");
			return mv;
		}
		List<Compra> compras = Arrays.asList(compra);
		ModelAndView mv = new ModelAndView("TelaPrincipalCliente");
		mv.addObject("listaDeClientes", clientes);
		mv.addObject("listaDePedidos", pedidos);
		mv.addObject("listaDePedidosDesejados", pedidosDesejados);
		mv.addObject("listaDeCompras", compras);
		
		System.out.println(id);
		return mv;
	}
	
	@RequestMapping("/cliente/login/pedido/{id}")
	public ModelAndView pedidoDesejado(@PathVariable Long id){
		
		Boolean teste = true;
		Pedido pedido = pedidoRepository.findById(id).get();
		if(pedido.getSelecionado()) {
			teste = false;
		}
		pedido.setSelecionado(true);

		pedidoRepository.save(pedido);
		
		Compra compra = compraRepository.findById(pedido.getIdCompra()).get();
		if(teste) {
			compra.setResult(compra.getResult()+pedido.getPrice());
		}
		compraRepository.save(compra);
		
		Cliente cliente = clienteRepository.findById(pedido.getIdcliente()).get();
		
	
		
		List<Cliente> clientes = Arrays.asList(cliente);
	//	List<Prato> pratos = pratoService.listarPratos();
		List<Pedido> pedidosDesejados = new ArrayList<Pedido>();
		List<Compra> compras = Arrays.asList(compra);
		
		List<Pedido> pedidos = pedidoRepository.findAll();
		for(Pedido p : pedidos) {
			if(p.getSelecionado()) {
				
				pedidosDesejados.add(p);
			}
		}
		
		if(clientes.isEmpty()) {
			ModelAndView mv = new ModelAndView("LoginClienteFail");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("TelaPrincipalCliente");
		mv.addObject("listaDeClientes", clientes);
		mv.addObject("listaDePedidos", pedidos);
		mv.addObject("listaDePedidosDesejados", pedidosDesejados);
		mv.addObject("listaDeCompras", compras);
		
		System.out.println(id);
		return mv;
	}
	
	@RequestMapping("/cliente/login/finalizarcompra/{id}")
	public String finalizarCompra(@PathVariable Long id){
		
		
		Compra compra = compraRepository.findById(id).get();

		
		List<Pedido> pedidos = pedidoRepository.findAll();
		String id_pedidos="";
		for(Pedido pedido : pedidos) {
			if(pedido.getSelecionado()) {
				id_pedidos+=pedido.getIdprato()+"-";
				compra.setIdcliente(pedido.getIdcliente());
			}
		}
		
		String array[] = id_pedidos.split("-");
		System.out.println("Feijao:"+array[0]);
		
		compra.setPratos(id_pedidos);
		compraRepository.save(compra);
		
		pedidoRepository.deletePedidos(pedidos.get(0).getIdcliente());
		
		System.out.println(id);
		return "CompraSucesso";
	}
	
	@RequestMapping("/cliente/login/vizualizarcompras/{id}")
	public ModelAndView vizualizarCompras(@PathVariable Long id){
		
		System.out.println(id);
		List<Compra> compras = compraRepository.findCompras(id);
		
		
		ModelAndView mv = new ModelAndView("ListagemComprasCliente");
		mv.addObject("listaDeCompras", compras);
		return mv;
	}
	
}


