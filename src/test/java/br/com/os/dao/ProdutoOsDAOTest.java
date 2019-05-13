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
		//TESTADO - OK  BANCO GERANDO AUTOINCREMENTO - 13/05  
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		
		ProdutoOS p1 = new ProdutoOS();
		p1.setDescricao("produto p1 ");
		p1.setAnoReferencia("2011");
		p1.setValorPorHora(new BigDecimal("56.35"));
		
		pdao.salvar(p1);
		
		ProdutoOS p2 = new ProdutoOS();
		p2.setDescricao("produto p2 ");
		p2.setAnoReferencia("2011");
		p2.setValorPorHora(new BigDecimal("104.21"));
		
		pdao.salvar(p2);
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
		produtoOS.setCodigoProduto(1L);
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
		produtoOS.setCodigoProduto(1L);
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		pdao.excluir(produtoOS);;
	}
	
	
	
	

}
