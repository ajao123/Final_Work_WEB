package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	@Query(value = "select * from cliente c where c.email = :email && c.senha = :senha", nativeQuery = true)
	List<Cliente> findCliente(@Param("email") String email, @Param("senha") String senha);
}
