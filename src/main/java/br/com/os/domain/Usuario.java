package br.com.os.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity()
@Table(name="usuario")
public class Usuario  {
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@Column(name = "cod_usuario", columnDefinition= "serial", unique=true, nullable=false)
	@GeneratedValue(strategy= GenerationType.SEQUENCE ,generator="pk_sequence")
	private Integer codigoUsuario;

	public Integer getCodigo() {
		return codigoUsuario;
	}

	public void setCodigo(Integer codigo) {
		this.codigoUsuario = codigo;
	}
	
	@Column(length=100, nullable = false)
	private String nome;
	@Column(length=32, nullable = false)
	private String senha;
	
	
	private String segSenha;

	@Enumerated(EnumType.STRING)
	private Role role;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getSegSenha() {
		return segSenha;
	}
	
	public void setSegSenha(String segSenha) {
		this.segSenha = segSenha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigoUsuario == null) {
			if (other.codigoUsuario != null)
				return false;
		} else if (!codigoUsuario.equals(other.codigoUsuario))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (role != other.role)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigoUsuario=" + codigoUsuario + ", nome=" + nome + ", senha=" + senha + ", role=" + role
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
