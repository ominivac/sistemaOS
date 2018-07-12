package br.com.os.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.OS;
import br.com.os.domain.ResponsavelOS;
import br.com.os.domain.Usuario;

public class OsDAOTest {

	@Test
	@Ignore
	public void salvar() {
		OS os = new OS();
		os.setDataLancamento(new Date());
		os.setDataPrevisaoEntrega(new Date());
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		ResponsavelOS resp = rdao.buscarPorCodigo(6);
		
		os.setResponsavelOS(resp);
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.buscarPorCodigo(1);
		
		os.setUsuario(u);
		
		OsDAO osDAO = new OsDAO();
		osDAO.salvar(os);
		
		
	}
	
	
	@Test
	@Ignore
	public void editar() {
		//TESTADO OK
		OsDAO osDAO = new OsDAO();
		OS os = osDAO.buscarPorCodigo(19);
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		ResponsavelOS resp = rdao.buscarPorCodigo(5);
		
		os.setResponsavelOS(resp);
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.buscarPorCodigo(1);
		
		os.setUsuario(u);
		
		
		osDAO.editar(os);
		
		
	}
	
	
	@Test
	//@Ignore
	public void listar() {
		//TESTADO - OK 
		OsDAO osDAO = new OsDAO();
		//List<OS> listaOs = osDAO.listar();
		List<OS> listaOs = osDAO.listarbYDate();
		
		System.out.println(listaOs);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		//TESTADO OK
		OsDAO osDAO = new OsDAO();
		OS os = osDAO.buscarPorCodigo(13);
		System.out.println(os);
	}
	
	
	
	
	
}
