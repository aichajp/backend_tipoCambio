package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.edu.gt.progra2.HelloWorld.Dto.UsuariosDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/{id}")
	public ResponseEntity<UsuariosDto> obtenerUsuario(@PathVariable Long id) {
		UsuariosDto usuario = new UsuariosDto(id, "Walter", "Apellido");
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuariosDto> actualizarUsuario(@PathVariable Long id,
			@RequestBody UsuariosDto usuario) {
		usuario.setId(id);
		return ResponseEntity.ok(usuario);
	}
	
}
