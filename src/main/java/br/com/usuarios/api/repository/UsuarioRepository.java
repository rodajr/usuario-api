package br.com.usuarios.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usuarios.api.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByExclusaoNull();
}
