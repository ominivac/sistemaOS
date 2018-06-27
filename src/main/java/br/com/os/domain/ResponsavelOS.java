package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity()
@Table(name="responsavel_os")
public class ResponsavelOS {
	
	
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
	
	

}
