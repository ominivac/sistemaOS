package br.com.os.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;

public class ItemDAOTest {
	
	@Test
	//@Ignore
	public void salvar() {
		//TESTADO OK
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS produtoOS1 = pdao.buscarPorCodigo(2);
		ProdutoOS produtoOS2 = pdao.buscarPorCodigo(3);
		
		OsDAO osdao = new OsDAO();
		OS os = osdao.buscarPorCodigo(19);
		
		Item item1 = new Item();
		item1.setProdutoOS(produtoOS1);
		item1.setQuantidade(2);
		item1.setValorParcial(new BigDecimal("224.68"));
		item1.setOs(os);
		
		Item item2 = new Item();
		item2.setProdutoOS(produtoOS2);
		item2.setQuantidade(1);
		item2.setValorParcial(new BigDecimal("47.00"));
		item2.setOs(os);
		
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.salvar(item1);
		itemDAO.salvar(item2);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		//TESTADO OK
		ItemDAO itemDAO = new ItemDAO();
		List<Item> listaItens = itemDAO.listar();
		System.out.println(listaItens);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		//TESTADO OK
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.buscarPorCodigo(21);
		System.out.println(item);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		//TESTADO OK
		ItemDAO itemDAO = new ItemDAO();
		Item item1 = itemDAO.buscarPorCodigo(21);
		itemDAO.excluir(item1);
		
		Item item2 = itemDAO.buscarPorCodigo(22);
		itemDAO.excluir(item2);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		//TESTADO OK
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS produtoOS1 = pdao.buscarPorCodigo(2);
		ProdutoOS produtoOS2 = pdao.buscarPorCodigo(3);
		
		OsDAO osDAO = new OsDAO();
		OS os = osDAO.buscarPorCodigo(19);
		
		ItemDAO itemDAO = new ItemDAO();
		Item item1 = itemDAO.buscarPorCodigo(21);
		
		item1.setProdutoOS(produtoOS1);
		item1.setQuantidade(3);
		item1.setValorParcial(new BigDecimal("337.02"));
		item1.setOs(os);
		
		Item item2 = itemDAO.buscarPorCodigo(22);
		item2.setProdutoOS(produtoOS2);
		item2.setQuantidade(2);
		item2.setValorParcial(new BigDecimal("94.00"));
		
		itemDAO.editar(item1);
		itemDAO.editar(item2);
		
	}
	

}
