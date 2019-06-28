package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	@Query(value = "select * from compra c where c.idcliente = :idcliente", nativeQuery = true)
	List<Compra> findCompras(@Param("idcliente") Long idcliente);
}
