package br.com.os.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Item extends GenericDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valorParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProdutoOS produtoOS;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private OS os;

	
	
}
