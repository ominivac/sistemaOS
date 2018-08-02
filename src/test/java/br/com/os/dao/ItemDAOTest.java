package br.com.os.dao;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;

public class ItemDAOTest {
	
	@Test
	@Ignore
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
		item1.setValorParcial();
		//item1.setValorParcial(new BigDecimal("224.68"));
		item1.setOs(os);
		
		Item item2 = new Item();
		item2.setProdutoOS(produtoOS2);
		item2.setQuantidade(1);
		item2.setValorParcial();
		//item2.setValorParcial(new BigDecimal("47.00"));
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
		Item item = itemDAO.buscarPorCodigoItem(21);
		System.out.println(item);
		
	}
	
	@Test
	//@Ignore
	public void buscarPorOsEproduto() {
		//TESTADO OK
		int codigoOs = 116;
		int codigoProd = 86;
		
		ItemDAO itemDAO = new ItemDAO();
		Item item1 = itemDAO.buscarPorOsEproduto(codigoOs, codigoProd);
		
		System.out.println(item1);
		
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		//TESTADO OK
		ItemDAO itemDAO = new ItemDAO();
		Item item1 = itemDAO.buscarPorCodigoItem(21);
		itemDAO.excluir(item1);
		
		Item item2 = itemDAO.buscarPorCodigoItem(22);
		itemDAO.excluir(item2);
		
	}
	
	
	
	@Test
	@Ignore
	public void editar() {
		// TESTADO - OK
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS produtoOS1 = pdao.buscarPorCodigo(85);
		ProdutoOS produtoOS2 = pdao.buscarPorCodigo(86);
		
		OsDAO osDAO = new OsDAO();
		OS os = osDAO.buscarPorCodigo(62);
		
		ItemDAO itemDAO = new ItemDAO();
		Item itemARemover = itemDAO.buscarPorCodigoItem(63); //sera deletada
		
		
		Item itemAlterar = itemDAO.buscarPorCodigoItem(64); //alterar para 2
		itemAlterar.setQuantidade(2);
		
		//novos itens a adicionar
		Item item1 = new Item();
		
		item1.setProdutoOS(produtoOS1);
		item1.setQuantidade(3);
		item1.setValorParcial();
		
		item1.setOs(os);
		
		Item item2 = new Item();
		item2.setProdutoOS(produtoOS2);
		item2.setQuantidade(2);
		item2.setValorParcial();
		
		item2.setOs(os);
		
		itemDAO.excluir(itemARemover);
		itemDAO.update(itemAlterar);
		itemDAO.salvar(item1);
		itemDAO.salvar(item2);
		
	}
	

}
