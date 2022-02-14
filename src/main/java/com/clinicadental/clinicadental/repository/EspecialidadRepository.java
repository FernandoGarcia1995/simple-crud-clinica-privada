package com.clinicadental.clinicadental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicadental.clinicadental.entity.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer>  {

	boolean existsByCodEspecialidad(String codEspecialidad);;
	
}
