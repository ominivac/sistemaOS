package br.com.os.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Usuario;


public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setNome("roberto");
		usuario.setTipo('A');
		usuario.setSenha("123");
		
		
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
		String nome = "roberto";
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
		udeletado.setTipo('a');
		udeletado.setCodigo(cod);
		
		usuarioDAO.excluir(udeletado);
	}
	
	@Test
	public void editar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario ueditado = new Usuario();
		
		ueditado.setCodigo(3);
		ueditado.setNome("NOME EDITADO");
		ueditado.setSenha("SENHA EDITADA");
		ueditado.setTipo('U');
		
		usuarioDAO.editar(ueditado);
		
		
	}
	
	
	
	
	
}
