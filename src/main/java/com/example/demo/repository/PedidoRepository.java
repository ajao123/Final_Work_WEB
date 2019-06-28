package com.example.demo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Pedido;

@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	@Modifying
	@Query(value = "delete from pedido where idcliente = :idcliente", nativeQuery = true)
	void deletePedidos(@Param("idcliente") Long idcliente);
	
	@Modifying
	@Query(value = "update pedido set selecionado = true where id = :id", nativeQuery = true)
	void addPedido(@Param("id") Long id);
	
	@Modifying
	@Query(value = "update pedido set selecionado = false where id = :id", nativeQuery = true)
	void rmPedido(@Param("id") Long id);
}
