package br.com.os.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer codigo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	
	
}
