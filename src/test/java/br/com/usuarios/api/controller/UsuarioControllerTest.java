package br.com.usuarios.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.usuarios.api.entity.Usuario;
import br.com.usuarios.api.exception.NaoEncontradoException;
import br.com.usuarios.api.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {
	static {
		System.setProperty("spring.config.location", "classpath:application-teste.properties"); 
	}
	
	@MockBean
	private UsuarioRepository repository;
	
	private UsuarioController controller;
	
	private Usuario getFakeUsuario(Long id)  {
		Usuario usuario = new Usuario();
		
		usuario.setId(id);
		usuario.setNome("Paulo" + id);
		usuario.setEmail("teste@teste.com");
		usuario.setLogin("paulo");
		usuario.setSenha("12345");
		usuario.setCriacao(new Date());
		
		return usuario;
	}
	
	private List<Usuario> getlistaFake() {
		List<Usuario> lista = new ArrayList<>();
		lista.add(this.getFakeUsuario(1L));
		lista.add(this.getFakeUsuario(2L));
		lista.add(this.getFakeUsuario(3L));
		lista.add(this.getFakeUsuario(4L));
		lista.add(this.getFakeUsuario(5L));
		
		return lista;
	}
	
	@Test
	public void criar() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//	
		Usuario usuario = this.getFakeUsuario(1L);
		usuario.setId(null);
		Optional<Usuario> usu = Optional.of(usuario);
		Mockito.when(repository.findById(1L)).thenReturn(usu);
		
		Usuario usuarioCriado = this.getFakeUsuario(1L);
		Mockito.when(repository.saveAndFlush(usuarioCriado)).thenReturn(usuarioCriado);
		
		ResponseEntity<Usuario> response = controller.criar(usuario);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		Assert.assertFalse(usuario.equals(usuarioCriado));
		Assert.assertFalse(usuario.equals(null));
	}
	
	@Test
	public void deletar( ) {
		Usuario usuario = this.getFakeUsuario(1L);
		Optional<Usuario> usu = Optional.of(usuario);
		Mockito.when(repository.findById(1L)).thenReturn(usu);
		
		Usuario usuarioDeletado = this.getFakeUsuario(1L);
		usuarioDeletado.setExclusao(new Date());
		usuarioDeletado.setModificacao(new Date());
		Mockito.when(repository.saveAndFlush(usuarioDeletado)).thenReturn(usuarioDeletado);
		
		ResponseEntity<Void> response = controller.deletar(1L);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	public void procurar( ) {
		Usuario usuario = this.getFakeUsuario(1L);
		Optional<Usuario> usu = Optional.of(usuario);
		Mockito.when(repository.findById(1L)).thenReturn(usu);
				
		ResponseEntity<Usuario> response = controller.procurar(1L);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		Assert.assertEquals(usuario.getId(), response.getBody().getId());
		Assert.assertEquals(usuario.getNome(), response.getBody().getNome());
		Assert.assertEquals(usuario.getEmail(), response.getBody().getEmail());
		Assert.assertEquals(usuario.getLogin(), response.getBody().getLogin());
		Assert.assertEquals(usuario.getSenha(), response.getBody().getSenha());
	}
	
	@Test(expected = NaoEncontradoException.class)
	public void procurarException() {
		Optional<Usuario> usuario = Optional.empty();
		Mockito.when(repository.findById(1L)).thenReturn(usuario);
		
		controller.procurar(1L);
	}
	
	@Test
	public void listar() {
		List<Usuario> usuario = this.getlistaFake();
		
		Mockito.when(repository.findByExclusaoNull()).thenReturn(usuario);
				
		ResponseEntity<List<Usuario>> response = controller.listar();
		System.out.println("booyah");
		System.out.println(response);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		Assert.assertEquals(usuario.size(), response.getBody().size());
		
	}
}