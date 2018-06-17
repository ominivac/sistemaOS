package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	
	@Column(length=100, nullable = false)
	private String nome;
	@Column(length=32, nullable = false)
	private String senha;
	
	
	private String segSenha;

	@Column(nullable = false)
	private Character tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getSegSenha() {
		return segSenha;
	}
	
	public void setSegSenha(String segSenha) {
		this.segSenha = segSenha;
	}
	
	public Character getRole() {
		return tipo;
	}

	public void setRole(Character tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", tipo=" + tipo + ", getCodigo()=" + getCodigo() + "]";
	}
	
	
	
	
	
	
	
}
