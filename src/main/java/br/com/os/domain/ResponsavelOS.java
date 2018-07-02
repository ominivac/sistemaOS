package br.com.os.domain;

import java.io.Serializable;

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
@Table(name="responsavel_os")
@NamedQueries({
	@NamedQuery(name="ResponsavelOs.listar", query= "SELECT responsavelOs FROM ResponsavelOS responsavelOs"),
	@NamedQuery(name="ResponsavelOs.buscarPorCodigo", query= "SELECT responsavelOs FROM ResponsavelOS responsavelOs WHERE responsavelOs.codigoResponsavel = :codigo" )
})
public class ResponsavelOS implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_responsavel", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.SEQUENCE ,generator="pk_sequence")
	private Integer codigoResponsavel;

	public Integer getCodigo() {
		return codigoResponsavel;
	}

	public void setCodigo(Integer codigo) {
		this.codigoResponsavel = codigo;
	}
	
	
	@Column(length=100, nullable = true)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "ResponsavelOS [codigoResponsavel=" + codigoResponsavel + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoResponsavel == null) ? 0 : codigoResponsavel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		ResponsavelOS other = (ResponsavelOS) obj;
		if (codigoResponsavel == null) {
			if (other.codigoResponsavel != null)
				return false;
		} else if (!codigoResponsavel.equals(other.codigoResponsavel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
