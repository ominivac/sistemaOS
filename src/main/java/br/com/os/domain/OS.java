package br.com.os.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OS extends GenericDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="data_lancamento", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;
	
	
	@Column(name="data_previsao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPrevisaoEntrega;
	
	@Column(name = "valor_total", precision = 8, scale = 2)
	private BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private ResponsavelOS responsavelOS;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(Date dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ResponsavelOS getResponsavelOS() {
		return responsavelOS;
	}

	public void setResponsavelOS(ResponsavelOS responsavelOS) {
		this.responsavelOS = responsavelOS;
	}

	
	
	
	
}
