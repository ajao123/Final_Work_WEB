package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idcliente;
	private Long idprato;
	private Long idCompra;
	private String name;
	private Double price;
	private Boolean selecionado;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Long idcliente, Long idprato, Long idCompra, String name, Double price, Boolean selecionado) {
		this.idcliente = idcliente;
		this.idprato = idprato;
		this.idCompra = idCompra;
		this.name = name;
		this.price = price;
		this.selecionado = selecionado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Long getIdprato() {
		return idprato;
	}

	public void setIdprato(Long idprato) {
		this.idprato = idprato;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	
	
}