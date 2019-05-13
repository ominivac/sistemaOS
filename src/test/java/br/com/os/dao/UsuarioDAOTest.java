package br.com.os.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Role;
import br.com.os.domain.Usuario;


public class UsuarioDAOTest {
	
	@Test
	//@Ignore
	public void salvar() {
		// TESTADO SEM ID - BANCO GERANDO AUTOINCREMENTO COM TIPO LONG - 13/05  
		
		Usuario u1 = new Usuario();
		u1.setNome("Roberto Sousa");
		u1.setRole(Role.ADMINISTRADOR);
		u1.setEmail("robertokbs@gmail.com");
		u1.setSenhaSemCripto("1q2w3e");
		
		SimpleHash hash1 = new SimpleHash("md5", u1.getSenhaSemCripto() );
		u1.setSenha(hash1.toHex() );
		

		Usuario u2 = new Usuario();
		u2.setNome("admin");
		u2.setRole(Role.ADMINISTRADOR);
		u2.setEmail("admin@gmail.com");
		u2.setSenhaSemCripto("admin");
		
		SimpleHash hash2 = new SimpleHash("md5", u2.getSenhaSemCripto() );
		u2.setSenha(hash2.toHex() );
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(u1);
		usuarioDAO.salvar(u2);
		
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
	@Ignore
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
		//String  email = "cris.torres@gmail.com";
		String  email = "robertokbs@gmail.com";
		//String  senha = "cris123";
		String  senha = "1q2w3e";
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.login(email, senha);
		
		assertNotNull(u);
		System.out.println(u);
		
	}
	
	
	@Test
	@Ignore
	public void mergeIncluir() {
		//TESTADO - OK
		Usuario usuario = new Usuario();
	//	usuario.setCodigoUsuario(1);
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
		Long cod = 2L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario udeletado = new Usuario();
		udeletado.setNome("rer");
		udeletado.setSenha("jjds");
		udeletado.setRole(Role.USUARIO);
		udeletado.setCodigoUsuario(cod);
		
		usuarioDAO.excluir(udeletado);
	}
	
	
	
	
	
	
	
}
