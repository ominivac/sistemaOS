package br.com.os.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.os.dao.UsuarioDAO;

import br.com.os.domain.Usuario;


@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuarioLogado = new Usuario();
	}
	
	
	public void autenticar() {
		try {
			UsuarioDAO udao = new UsuarioDAO();
			usuarioLogado =  udao.login(usuario.getNome() , usuario.getSenhaSemCripto() );
			if(usuarioLogado == null) {
				Messages.addGlobalError("Nome de usuário ou senha inválidas" );
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
			
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage() );
		}
		
	}
	
	
	public boolean temPermisssoes(List<String> permissoes) {
		
		for(String permissao : permissoes) {
			if(usuarioLogado.getRole().toString().equals(permissao)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
