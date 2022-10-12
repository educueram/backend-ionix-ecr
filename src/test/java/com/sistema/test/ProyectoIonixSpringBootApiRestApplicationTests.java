package com.sistema.test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eduardo.cuevas.entidades.Usuario;
import com.eduardo.cuevas.repositorio.UsuarioRepositorio;

@SpringBootTest
class ProyectoIonixSpringBootApiRestApplicationTests {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	Usuario usuario = new Usuario();
	String nombre = "Eduardo";
	String apellido = "Cuevas";
	String username = "eduardo.cuevas";
	String email = "eduardo@gmail.com";
	String password = "admin";
	byte avatar = 1; 
	
	@Test
	void consultaUsuarioTest() {
		usuario.setFirstName(nombre);
		usuario.setLastName(apellido);
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setAvatar(avatar);		
	}

}
