package br.com.os.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class OS extends GenericDomain{

	@Column(name="data_lancamento", nullable=false)
	private Date dataLancamento;
	
	private ProdutoOS itemOS;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public ProdutoOS getItemOS() {
		return itemOS;
	}

	public void setItemOS(ProdutoOS itemOS) {
		this.itemOS = itemOS;
	}
	
	
	
}
