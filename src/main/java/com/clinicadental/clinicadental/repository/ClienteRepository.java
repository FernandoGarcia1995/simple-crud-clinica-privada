package com.clinicadental.clinicadental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clinicadental.clinicadental.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("FROM Cliente t where t.dni like CONCAT('%',:dni,'%')") 
	Page<Cliente> findAllByDni(Pageable pageable , @Param("dni") String dni);
	
	boolean existsByDni(String dni);

}
