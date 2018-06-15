package br.com.os.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.ResponsavelOS;

public class ResponsavelDAOTest {

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
	public void editar() {
		//testado - ok
		ResponsavelOS r = new ResponsavelOS();
		r.setCodigo(1);
		r.setNome("responsável 1 editado");
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		rdao.editar(r);
	}
	
	@Test
	public void deletar() {
		ResponsavelOS r = new ResponsavelOS();
		r.setCodigo(4);
		r.setNome("T"); //esta requirindo campo mesmo para deleção
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		rdao.excluir(r);
		
	}
	
	
	
}
