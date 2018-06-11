package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	
	@Column(length=50, nullable = false)
	private String nome;
	@Column(length=32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;
	
	
}
