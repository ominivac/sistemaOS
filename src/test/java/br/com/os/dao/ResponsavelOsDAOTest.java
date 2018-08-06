package br.com.os.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.ResponsavelOS;

public class ResponsavelOsDAOTest {

	@Test
	@Ignore
	public void salvar() {
		//testado - ok
		ResponsavelOS r = new ResponsavelOS();
		r.setNome("TESTE");
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		rdao.salvar(r);
	}
	
	@Test
	@Ignore
	public void listar() {
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		List<ResponsavelOS> lista = rdao.listar();
		
		for(ResponsavelOS responsavelOS : lista) {
			System.out.println(responsavelOS);
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		ResponsavelOS responsavelOS =  rdao.buscarPorCodigo(5);
		System.out.println(responsavelOS);
		
		
	}
	
	/***
	public void editar() {
		//testado - ok
		ResponsavelOS r = new ResponsavelOS();
		r.setCodigo(1);
		r.setNome("responsável 1 editado");
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		rdao.editar(r);
	} **/
	
	
	
	
	/**
	public void deletar() {
		//testado  - ok
		ResponsavelOS r = new ResponsavelOS();
		r.setCodigo(4);
		r.setNome("T"); //esta requirindo campo mesmo para deleção
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		rdao.excluir(r);
		
	}*/
	
	
	
}
