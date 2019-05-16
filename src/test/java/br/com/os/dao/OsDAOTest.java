package br.com.os.dao;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;
import br.com.os.domain.Usuario;
import br.com.os.filter.OSFilter;

public class OsDAOTest {

	@Test
	//@Ignore
	public void salvar() {
		OS os = new OS();
		os.setDataSolicitacao(new Date() );
		os.setDataPrevisaoEntrega(new Date());
		
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.buscarPorCodigo(2);
		
		os.setUsuario(u);
		/*
		ProdutoOsDAO pdao = new ProdutoOsDAO();
		ProdutoOS p1 = pdao.buscarPorCodigo(1);
		ProdutoOS p2 = pdao.buscarPorCodigo(2);
		
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
		
		
		System.out.println("valor total da os INSERT" + os.getValorTotal() );
		*/
		
		
		OsDAO osDAO = new OsDAO();
		//osDAO.salvar(os, itens);
		osDAO.salvar(os);
		
		
		
	}
	
	
	@Test
	@Ignore
	public void editar() {
		OS os = new OS();
		OsDAO osDAO = new OsDAO();
		
		//pesquisa a os que deseja edicao pelo cod
		os = osDAO.buscarPorCodigo(7);
		
		System.out.println(os);
		
		List<Item> itens =  os.getItensOs();
		System.out.println(itens);
		
		ItemDAO idao = new ItemDAO();
		Item itemRemover = idao.buscarPorCodigoItem(18);
		
		int achou = -1;
		
		for(int i=0 ; i <  itens.size() ; i++) {
			
			if( itemRemover.getCodigoItem() == itens.get(i).getCodigoItem() ) {
				achou = i;
				System.out.println("achou na posicao " + achou );
				itens.remove(achou);
				
			}
		}
		
		os.setItensOs(itens);
		idao.excluir(itemRemover);
		
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
	public void listarPorAtividade() {
		//TESTADO - OK 
		OsDAO osDAO = new OsDAO();
		List<OS> listaOs = osDAO.buscarPorAtividade("NORDESTE");
		
		System.out.println(listaOs);
		
	}
	
	@Test
	@Ignore
	public void listarPorAtividadeItem() {
		//TESTADO - OK 
		OsDAO osDAO = new OsDAO();
		List<OS> listaOs = osDAO.buscarPorAtividadeItem("GRAVAÇÃO");
		
		System.out.println(listaOs);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		//TESTADO OK
		OsDAO osDAO = new OsDAO();
		OS os = osDAO.buscarPorCodigo(27);
		System.out.println(os);
	}
	
	@Test
	@Ignore
	public void buscarPorIntervaloData() throws ParseException {
		SimpleDateFormat formato = new  SimpleDateFormat("dd/MM/YYYY");
		
		
		
		String data_inicial = "1/1/2018";
		String data_final = "31/12/2018";
		
		
		Date  startDate = (Date)formato.parse(data_inicial);
		//System.out.println(startDate);
		Date  endDate = (Date)formato.parse(data_final);
	//	System.out.println(endDate);
		
		GregorianCalendar cinicial = new GregorianCalendar();
		cinicial.set(2018, GregorianCalendar.JANUARY, 1);
		
		GregorianCalendar cfinal = new GregorianCalendar();
		cfinal.set(2018, GregorianCalendar.AUGUST, 20);
		
		
		
		Date d1 = cinicial.getTime();
		Date d2 = cfinal.getTime();
		
		
		System.out.println(d1+" " + d2);
		
		
		OsDAO osdao = new OsDAO();
		String data1 = formato.format(d1);
		String data2 = formato.format(d2);
		
		List<OS> listaos = osdao.buscarEntreDatas(data1, data2);
		
		for(OS os : listaos) {
			System.out.println(os);
		}
		
		System.out.println(data1 + " "+ data2);
		
	}
	
	
	
	
}
