package br.com.os.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Role;
import br.com.os.domain.Usuario;


public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		// TESTADO  - OK
		Usuario usuario = new Usuario();
		usuario.setNome("USUARIO 1");
		usuario.setRole(Role.USUARIO);
		usuario.setSenha("user1");
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	@Test
	@Ignore
	public void mergeIncluir() {
		//TESTADO - OK
		Usuario usuario = new Usuario();
		usuario.setCodigo(1);
		usuario.setNome("Roberto Sousa");
		usuario.setRole(Role.ADMINISTRADOR);
		usuario.setSenha("admin123");
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.merge(usuario);
	}
	
	@Test
	@Ignore
	public void listar() {
		//TESTADO - OK
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();
		
		for(Usuario usuario : resultado) {
			System.out.println(usuario.getNome() );
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
	//TESTADO - OK
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		System.out.println(usuarioDAO.buscarPorCodigo(11) );
	}
	
	@Test
	@Ignore
	public void deletar() {
	//TESTADO - OK	
		int cod = 2;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario udeletado = new Usuario();
		udeletado.setNome("rer");
		udeletado.setSenha("jjds");
		udeletado.setRole(Role.USUARIO);
		udeletado.setCodigo(cod);
		
		usuarioDAO.excluir(udeletado);
	}
	
	
	
	
	
	
	
}
