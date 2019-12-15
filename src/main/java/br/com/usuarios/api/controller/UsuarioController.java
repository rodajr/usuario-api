package br.com.usuarios.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.usuarios.api.entity.Usuario;
import br.com.usuarios.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "usuario", protocols = "http")
@RestController
@RequestMapping(path = "/usuario/api")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@ApiOperation(value = "Criacao de usuario", notes = "Metodo responsavel por criacao novo usuario")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> criar(
			@ApiParam(value = "Objeto Usuario", required = true) 
			@Valid @RequestBody Usuario usuario) {
		
		usuario = service.criar(usuario);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@ApiOperation(value = "Procura usuario por id", notes = "Metodo responsavel procurar usuario pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> procurar(
			@ApiParam(value = "Id do Usuario", required = true) 
			@PathVariable Long id) {
		
		Usuario usuario = service.procurar(id);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@ApiOperation(value = "Lista todos os usuarios", notes = "Metodo responsavel listar todos usuarios")
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok().body(service.listar());
	}
	
	@ApiOperation(value = "Atualizacao de usuario", notes = "Metodo responsavel por atualizacao de usuario")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> atualizar(
			@ApiParam(value = "Objeto Usuario", required = true) 
			@Valid 
			@RequestBody Usuario usuario) {
		
		usuario = service.atualizar(usuario);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	
	@ApiOperation(value = "Deleta usuario por id", notes = "Metodo responsavel deletar usuario pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(
			@ApiParam(value = "Usuario Id", required = true) 
			@PathVariable Long id) {

		service.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
