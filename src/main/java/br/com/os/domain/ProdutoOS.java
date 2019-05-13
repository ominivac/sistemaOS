package br.com.os.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity()
@Table(name="produto_os")
@NamedQueries({
	@NamedQuery(name="ProdutoOS.listar", query= "SELECT produtoOs FROM ProdutoOS produtoOs"),
	@NamedQuery(name="ProdutoOS.buscarPorCodigo", query= "SELECT produtoOs FROM ProdutoOS produtoOs WHERE produtoOs.codigoProduto = :codigo" )
	
})
public class ProdutoOS implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	//@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_produto", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigoProduto;

	

	public Long getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

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

	@Override
	public String toString() {
		return "ProdutoOS [codigoProduto=" + codigoProduto + ", descricao=" + descricao + ", anoReferencia="
				+ anoReferencia + ", valorPorHora=" + valorPorHora + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoProduto == null) ? 0 : codigoProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoOS other = (ProdutoOS) obj;
		if (codigoProduto == null) {
			if (other.codigoProduto != null)
				return false;
		} else if (!codigoProduto.equals(other.codigoProduto))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
