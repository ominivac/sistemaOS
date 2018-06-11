package br.com.os.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoOS extends GenericDomain{
	
	@Column(length = 100, nullable=false)
	private String descricao;
	
	@Column(length = 4, nullable= false)
	private String anoReferencia;
	
	@Column(nullable=false, precision = 10 , scale = 2)
	private BigDecimal valorPorHora;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAnoReferencia() {
		return anoReferencia;
	}
	public void setAnoReferencia(String anoReferencia) {
		this.anoReferencia = anoReferencia;
	}
	public BigDecimal getValorPorHora() {
		return valorPorHora;
	}
	public void setValorPorHora(BigDecimal valorPorHora) {
		this.valorPorHora = valorPorHora;
	}
	
	
	
	
	

}
