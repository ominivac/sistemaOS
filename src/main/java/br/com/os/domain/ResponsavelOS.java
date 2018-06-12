package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ResponsavelOS extends GenericDomain{
	
	@Column(length=100, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
