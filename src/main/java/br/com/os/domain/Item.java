package br.com.os.domain;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="item")
@NamedQueries({
	@NamedQuery(name="Item.listar", query= "SELECT item FROM Item item"),
	@NamedQuery(name="Item.buscarPorCodigo", query= "SELECT item FROM Item item WHERE item.codigoItem = :codigo" )
})
public class Item implements Serializable {
	
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

	@Override
	public String toString() {
		return "Item [codigoItem=" + codigoItem + ", quantidade=" + quantidade + ", valorParcial=" + valorParcial
				+ ", produtoOS=" + produtoOS + ", os=" + os + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((produtoOS == null) ? 0 : produtoOS.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valorParcial == null) ? 0 : valorParcial.hashCode());
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
		Item other = (Item) obj;
		if (codigoItem == null) {
			if (other.codigoItem != null)
				return false;
		} else if (!codigoItem.equals(other.codigoItem))
			return false;
		if (os == null) {
			if (other.os != null)
				return false;
		} else if (!os.equals(other.os))
			return false;
		if (produtoOS == null) {
			if (other.produtoOS != null)
				return false;
		} else if (!produtoOS.equals(other.produtoOS))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valorParcial == null) {
			if (other.valorParcial != null)
				return false;
		} else if (!valorParcial.equals(other.valorParcial))
			return false;
		return true;
	}

	
	
	
	
	
	
}
