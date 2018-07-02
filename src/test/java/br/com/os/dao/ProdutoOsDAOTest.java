package br.com.os.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.ProdutoOS;



public class ProdutoOsDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		//TESTADO - OK
		ProdutoOS produtoOS = new ProdutoOS();
		produtoOS.setDescricao("produto p1 teste salvar");
		produtoOS.setAnoReferencia("2011");
		produtoOS.setValorPorHora(new BigDecimal("56.35"));
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		pdao.salvar(produtoOS);
	}
	
	@Test
	@Ignore
	public void listar() {
		//TESTADO - OK
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		List<ProdutoOS> lista = pdao.listar();
		
		for(ProdutoOS produtoOS : lista) {
			System.out.println(produtoOS);
		}
	}
	
	
	@Test
	@Ignore
	public void editar() {
		// TESTADO - OK 
		ProdutoOS produtoOS = new ProdutoOS();
		produtoOS.setCodigo(17);
		produtoOS.setDescricao("produto p1 teste editado");
		produtoOS.setAnoReferencia("2012");
		produtoOS.setValorPorHora(new BigDecimal("156.35"));
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		pdao.merge(produtoOS);
	}
	
	@Test
	@Ignore
	public void excluir() {
		// TESTADO - OK 
		ProdutoOS produtoOS = new ProdutoOS();
		produtoOS.setCodigo(17);
		produtoOS.setDescricao("produto p1 teste editado");
		produtoOS.setAnoReferencia("2012");
		produtoOS.setValorPorHora(new BigDecimal("156.35"));
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		pdao.excluir(produtoOS);;
	}
	
	
	
	

}
