package br.com.os.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name = "item")
@Table(name="item")
public class Item {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_item", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.SEQUENCE ,generator="pk_sequence")
	private Integer codigoItem;

	public Integer getCodigo() {
		return codigoItem;
	}

	public void setCodigo(Integer codigo) {
		this.codigoItem = codigo;
	}
	
	@Column(name="quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name="valor_parcial", nullable = false, precision = 8, scale = 2)
	private BigDecimal valorParcial;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="produto_cod",  referencedColumnName="cod_produto", nullable = false)
	private ProdutoOS produtoOS;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="os_cod", referencedColumnName="cod_os",  nullable = false)
	private OS os;

	

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public ProdutoOS getProdutoOS() {
		return produtoOS;
	}

	public void setProdutoOS(ProdutoOS produtoOS) {
		this.produtoOS = produtoOS;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	
	
	
	
	
}
