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
	@NamedQuery(name="Item.buscarPorCodigoItem", query= "SELECT item FROM Item item WHERE item.codigoItem = :codigo" ),
	@NamedQuery(name="Item.buscarPorCodOsAndCodProduto", query= "SELECT i FROM Item i WHERE i.os.codigoOs = :codOS AND i.produtoOS.codigoProduto = :codProd" )
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
	
	@Column(name="quantidade_horas", nullable = true)
	private Integer quantidadeHoras;
	
	
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
	
	
	public Integer getQuantidadeHoras() {
		return quantidadeHoras;
	}
	
	public void setQuantidadeHoras(Integer quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public BigDecimal getValorParcial() {
		valorParcial =  produtoOS.getValorPorHora().multiply(new BigDecimal( quantidade)).multiply( new BigDecimal(this.quantidadeHoras));
		return valorParcial;
	}

	

	public void setValorParcial() {
		
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
		return "Item [codigoItem=" + codigoItem + ", quantidade=" + quantidade + ", quantidadeHoras=" + quantidadeHoras
				+ ", valorParcial=" + valorParcial + ", produtoOS=" + produtoOS + ", os=" + os + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
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
		return true;
	}

	

	
	
	
	
	
	
}
