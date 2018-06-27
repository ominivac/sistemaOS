package br.com.os.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Role;
import br.com.os.domain.Usuario;


public class UsuarioDAOTest {
	
	@Test
	//@Ignore
	public void salvar() {
		// testado
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
		Usuario usuario = new Usuario();
		usuario.setCodigo(2);
		usuario.setNome("maria rita");
		usuario.setRole(Role.ADMINISTRADOR);
		usuario.setSenha("maria1234");
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();
		
		for(Usuario usuario : resultado) {
			System.out.println(usuario.getNome() );
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		//testado ok
		String nome = "ma";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		System.out.println(usuarioDAO.buscarPorNome(nome) );
	}
	
	@Test
	@Ignore
	public void deletar() {
		int cod = 2;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario udeletado = new Usuario();
		udeletado.setNome("rer");
		udeletado.setSenha("jjds");
		udeletado.setRole(Role.USUARIO);
		udeletado.setCodigo(cod);
		
		usuarioDAO.excluir(udeletado);
	}
	
	@Test
	@Ignore
	public void editar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario ueditado = new Usuario();
		
		ueditado.setCodigo(2);
		ueditado.setNome("Maria Rita");
		ueditado.setSenha("maria12345");
		ueditado.setRole(Role.ADMINISTRADOR);
		
		usuarioDAO.editar(ueditado);
		
		
	}
	
	
	
	
	
}
