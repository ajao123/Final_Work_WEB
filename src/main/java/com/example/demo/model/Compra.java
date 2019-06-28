package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idcliente;
	private String pratos;
	private Double result;
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(Long idcliente, String pratos, Double result) {
		this.idcliente = idcliente;
		this.pratos = pratos;
		this.result = result;
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

	public String getPratos() {
		return pratos;
	}

	public void setPratos(String pratos) {
		this.pratos = pratos;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	
	
}