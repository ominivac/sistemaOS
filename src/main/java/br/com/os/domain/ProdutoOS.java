package br.com.os.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name = "produto_os")
@Table(name="produto_os")
public class ProdutoOS {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_produto", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.SEQUENCE ,generator="pk_sequence")
	private Integer codigoProduto;

	public Integer getCodigo() {
		return codigoProduto;
	}

	public void setCodigo(Integer codigo) {
		this.codigoProduto = codigo;
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
	
	
	
	
	

}
