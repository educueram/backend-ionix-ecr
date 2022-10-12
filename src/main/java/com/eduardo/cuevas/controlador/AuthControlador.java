package com.eduardo.cuevas.controlador;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.cuevas.dto.LoginDTO;
import com.eduardo.cuevas.dto.RegistroDTO;
import com.eduardo.cuevas.entidades.Rol;
import com.eduardo.cuevas.entidades.Usuario;
import com.eduardo.cuevas.repositorio.RolRepositorio;
import com.eduardo.cuevas.repositorio.UsuarioRepositorio;
import com.eduardo.cuevas.seguridad.JWTAuthResonseDTO;
import com.eduardo.cuevas.seguridad.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private RolRepositorio rolRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthResonseDTO(token));
	}
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
		if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Nombre de usuario ya existe, intente con otro diferente.",HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Email de usuario ya existente, intente con otro diferente.",HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		usuario.setFirstName(registroDTO.getFirstName());
		usuario.setLastName(registroDTO.getLastName());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		usuario.setAvatar(registroDTO.getAvatar());
		
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
	}
	
	@PostMapping("/modificarUsuario")
	public ResponseEntity<?> modificarUsuario(@RequestBody RegistroDTO registroDTO){
		if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Nombre de usuario ya existe, intente con otro diferente.",HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Email de usuario ya existente, intente con otro diferente.",HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		usuario.setFirstName(registroDTO.getFirstName());
		usuario.setLastName(registroDTO.getLastName());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		usuario.setAvatar(registroDTO.getAvatar());
		
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario modificado exitosamente",HttpStatus.OK);
	}
	
	@PostMapping("/eliminarUsuario")
	public ResponseEntity<?> eliminarUsuario(@RequestBody RegistroDTO registroDTO){
				
		Usuario usuario = new Usuario();
		usuario.setUsername(registroDTO.getUsername());
		
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRepositorio.delete(usuario);
		return new ResponseEntity<>("Usuario eliminado exitosamente",HttpStatus.OK);
	}
}
