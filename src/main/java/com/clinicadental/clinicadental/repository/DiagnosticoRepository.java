package com.clinicadental.clinicadental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicadental.clinicadental.entity.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
	
	boolean existsByCodDiagnostico(String codDiagnostico);

}
