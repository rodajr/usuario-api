package br.com.usuarios.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
@DynamicUpdate
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1264369110333930223L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@JsonIgnore
	@Column(name = "data_criacao")
	private Date criacao;
	
	@JsonIgnore
	@Column(name = "data_modificacao")
	private Date modificacao;

	@JsonIgnore
	@Column(name = "data_exclusao")
	private Date exclusao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getModificacao() {
		return modificacao;
	}

	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}
	
	public Date getExclusao() {
		return exclusao;
	}

	public void setExclusao(Date exclusao) {
		this.exclusao = exclusao;
	}

	@Override
	public String toString() {
		return "{\nid : " + id + ", \nnome : " + nome + ", \nemail : " + email + ", \nlogin : " + login + ", \nsenha : "
				+ senha + ", \ncriacao : " + criacao + ", \nmodificacao : " + modificacao + ", \nexclusao : " + exclusao
				+ "\n}";
	}
	
}
