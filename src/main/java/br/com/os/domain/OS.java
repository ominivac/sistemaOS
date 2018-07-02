package br.com.os.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.os.converter.SampleEntity;

@Entity
@Table(name="os")
public class OS implements Serializable,SampleEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_os", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.SEQUENCE ,generator="pk_sequence")
	private Integer codigoOs;

	public Integer getCodigo() {
		return codigoOs;
	}

	
	public void setCodigo(Integer codigo) {
		this.codigoOs = codigo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_lancamento", nullable = true)
	private Date dataLancamento;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_previsao", nullable = true)
	private Date dataPrevisaoEntrega;
	
	@Column(name = "valor_total", precision = 8, scale = 2)
	private BigDecimal valorTotal;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_cod",referencedColumnName="cod_usuario",columnDefinition="integer", nullable = true)
	//a opcao columnDefinition é necessaria, caso contrario o campo sera criado como not null. bug versao hibernate?
	// https://stackoverflow.com/questions/15511899/cannot-make-manytoone-relationship-nullable / https://hibernate.atlassian.net/browse/HHH-8229
	private Usuario usuario;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="responsavel_cod", referencedColumnName="cod_responsavel",columnDefinition="integer", nullable = true)
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


	@Override
	public String toString() {
		return "OS [codigoOs=" + codigoOs + ", dataLancamento=" + dataLancamento + ", dataPrevisaoEntrega="
				+ dataPrevisaoEntrega + ", valorTotal=" + valorTotal + ", usuario=" + usuario + ", responsavelOS="
				+ responsavelOS + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoOs == null) ? 0 : codigoOs.hashCode());
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((dataPrevisaoEntrega == null) ? 0 : dataPrevisaoEntrega.hashCode());
		result = prime * result + ((responsavelOS == null) ? 0 : responsavelOS.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
		OS other = (OS) obj;
		if (codigoOs == null) {
			if (other.codigoOs != null)
				return false;
		} else if (!codigoOs.equals(other.codigoOs))
			return false;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (dataPrevisaoEntrega == null) {
			if (other.dataPrevisaoEntrega != null)
				return false;
		} else if (!dataPrevisaoEntrega.equals(other.dataPrevisaoEntrega))
			return false;
		if (responsavelOS == null) {
			if (other.responsavelOS != null)
				return false;
		} else if (!responsavelOS.equals(other.responsavelOS))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

	

	

	
	
	
	
}
