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
	
	@Column(name = "valor_total", precision = 8, scale = 2)
	private BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private ResponsavelOS responsavelOS;

	
	
	
	
}
