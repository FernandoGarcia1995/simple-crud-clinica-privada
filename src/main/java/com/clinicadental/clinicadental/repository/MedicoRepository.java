package com.clinicadental.clinicadental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clinicadental.clinicadental.entity.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
	
	boolean existsByDni(String dni);
	
	@Query("FROM Medico t where t.dni like CONCAT('%',:dni,'%')") 
	Page<Medico> findAllByDni(Pageable pageable , @Param("dni") String dni);

}
