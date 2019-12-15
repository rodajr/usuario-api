package br.com.usuarios.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.usuarios.api.entity.Usuario;
import br.com.usuarios.api.exception.NaoEncontradoException;
import br.com.usuarios.api.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario procurar(Long id) {
		Optional<Usuario> usuarioOptional = repository.findById(id);
		
		return usuarioOptional.orElseThrow(() -> new NaoEncontradoException("Objeto nao encontrado"));
	}
	
	public Usuario criar(Usuario usuario) {
		usuario.setId(null);
		usuario.setCriacao(new Date());
		
		return repository.save(usuario);
	}
	
	public Usuario atualizar(Usuario usuarioAtualizado) {
		Usuario usuario = this.procurar(usuarioAtualizado.getId());
		usuario.setNome(usuarioAtualizado.getNome());
		usuario.setEmail(usuarioAtualizado.getEmail());
		usuario.setLogin(usuarioAtualizado.getLogin());
		usuario.setSenha(usuarioAtualizado.getSenha());
		usuario.setModificacao(new Date());

		return repository.save(usuario);
	}
	
	public void deletar(Long id) {
		Usuario usuario = this.procurar(id);
		usuario.setExclusao(new Date());
		usuario.setModificacao(new Date());
		
		repository.save(usuario);
	}

	public List<Usuario> listar() {
		return repository.findByExclusaoNull();
	}
}
