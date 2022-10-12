package com.eduardo.cuevas.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.cuevas.entidades.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
	
}
