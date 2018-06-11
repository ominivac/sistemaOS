package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Item {
	
	
	private ProdutoOS produtoOS;
	
	@Column(nullable = false)
	private Short quantidade;

}
