package br.com.os.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
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
		usuario.setNome("USUARIO CRIPTO");
		usuario.setRole(Role.ADMINISTRADOR);
		usuario.setSenhaSemCripto("1q2w3e4r");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCripto());
		usuario.setSenha(hash.toHex() );
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	@Ignore
	public void salvar2() {
		// TESTADO  - OK
		Usuario usuario = new Usuario();
		usuario.setNome("Maria Rita");
		usuario.setRole(Role.USUARIO);
		usuario.setSenhaSemCripto("maria123");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCripto());
		usuario.setSenha(hash.toHex() );
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	//@Ignore
	public void autenticar() {
		String  nome = "Maria Rita";
		String  senha = "maria123";
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.login(nome, senha);
		
		System.out.println(u);
		
	}
	
	@Test
	@Ignore
	public void login() {
		String  nome = "admin";
		String  senha = "admin";
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.login(nome, senha);
		
		System.out.println(u);
		
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
