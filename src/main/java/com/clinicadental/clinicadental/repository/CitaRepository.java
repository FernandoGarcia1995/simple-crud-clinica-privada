package com.clinicadental.clinicadental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clinicadental.clinicadental.entity.Cita;

@Repository
public interface CitaRepository  extends JpaRepository<Cita, Integer> {
	
	@Query("FROM Cita t where t.codCita like CONCAT('%',:codigoCita,'%')") 
	Page<Cita> findAllByCodCita(Pageable pageable , @Param("codigoCita") String codigoCita);

}
