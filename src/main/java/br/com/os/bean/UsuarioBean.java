package br.com.os.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.os.dao.UsuarioDAO;
import br.com.os.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> usuarios ;
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void novo() {
		usuario = new Usuario();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO udao = new UsuarioDAO();
			usuarios =  udao.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar usuários");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
			
			novo();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar usuário");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
