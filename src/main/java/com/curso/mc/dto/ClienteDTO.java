package com.curso.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.mc.domain.Cliente;
import com.curso.mc.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Campo de preenchimento obrigatório")
	@Length(min=10, max=100)
	private String nome;
	@NotEmpty(message="Campo de preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;

	public ClienteDTO() {
	}

	public ClienteDTO(String nome, String email) {
		
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}
