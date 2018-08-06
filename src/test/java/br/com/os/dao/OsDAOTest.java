package br.com.os.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;
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
		ResponsavelOS resp = rdao.buscarPorCodigo(82);
		
		os.setResponsavelOS(resp);
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.buscarPorCodigo(42);
		
		os.setUsuario(u);
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS p1 = pdao.buscarPorCodigo(86);
		ProdutoOS p2 = pdao.buscarPorCodigo(85);
		
		Item i1 = new Item();
		i1.setProdutoOS(p1);
		i1.setQuantidade(2);
		i1.getValorParcial();
		
		//i1.setValorParcial(new BigDecimal("446.68"));
		
		Item i2 = new Item();
		i2.setProdutoOS(p2);
		i2.setQuantidade(1);
		i2.getValorParcial();
		//i2.setValorParcial(new BigDecimal("82.55"));
		
		List<Item> itens = new ArrayList<Item>();
		itens.add(i1);
		itens.add(i2);
		
		os.setItensOs(itens);
		
		
		System.out.println(os.getValorTotal() );
		
		
		OsDAO osDAO = new OsDAO();
		osDAO.salvar(os, itens);
		
		
	}
	
	
	@Test
	@Ignore
	public void editar() {
		OS os = new OS();
		OsDAO osDAO = new OsDAO();
		
		//pesquisa a os que deseja edicao pelo cod
		os = osDAO.buscarPorCodigo(120);
		
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		ResponsavelOS resp = rdao.buscarPorCodigo(83);
		
		//seta outro resp.
		os.setResponsavelOS(resp);
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.buscarPorCodigo(52);
		
		//outro usuario alterou
		os.setUsuario(u);
		
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS p1 = pdao.buscarPorCodigo(2);
		ProdutoOS p2 = pdao.buscarPorCodigo(3);
		
		ItemDAO idao = new ItemDAO();
		
		Item i1 = new Item();
		i1.setProdutoOS(p1);
		i1.setQuantidade(1);
		i1.getValorParcial();
		
		i1.setOs(os);//importante para o update !
		
		
		Item i2 = new Item();
		i2.setProdutoOS(p2);
		i2.setQuantidade(4);
		i2.getValorParcial();
		
		i2.setOs(os); //importante para o update !

		//faz traz os itens da os para edicao
		List<Item> listaNovosItens =  new ArrayList<Item>();
		listaNovosItens = os.getItensOs();
		
		
		int achou = -1;
		Item itemSelecionado = new Item();
		ProdutoOS premover = new ProdutoOS();
		premover.setCodigo(85);
		itemSelecionado.setProdutoOS(premover);
		
		for(int posicao = 0 ; posicao < listaNovosItens.size() ; posicao++) {
			if(listaNovosItens.get(posicao).getProdutoOS().getCodigo() == itemSelecionado.getProdutoOS().getCodigo() ) {
				achou = posicao;
			}
		}
		
		if(achou > -1) {
			//remove o item procurado
			Item itemToRemove = idao.buscarPorOsEproduto(os.getCodigo(), 85);
			idao.excluir(itemToRemove);
			listaNovosItens.remove(achou);
		}
		
		//remove o item com produto de codigo 85
		
		
		listaNovosItens.add(i1);
		listaNovosItens.add(i2);
		
		os.setItensOs(listaNovosItens);
		
		osDAO.editar(os); //ja 
		
		
		
		
		
	}
	
	
	@Test
	@Ignore
	public void listar() {
		//TESTADO - OK 
		OsDAO osDAO = new OsDAO();
		//List<OS> listaOs = osDAO.listar();
		List<OS> listaOs = osDAO.listarbYDate();
		
		//System.out.println(listaOs);
		
		for(int i=0 ; i < listaOs.size() ; i++) {
			OS os = listaOs.get(i);
			System.out.println(os);
			
			List<Item> itens= listaOs.get(i).getItensOs();
			for(int j=0 ; j < itens.size() ; j++) {
				Item item = itens.get(j);
				System.out.println(item);
			}
			System.out.println( "\n");
			
		}
			
		
		
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
