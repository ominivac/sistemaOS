package br.com.os.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.os.dao.OsDAO;
import br.com.os.dao.ProdutoOsDAO;
import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;

@ManagedBean
@ViewScoped
public class OsBeanEditar implements Serializable{

	private static final long serialVersionUID = 1L;
	private OS osEditar;
	private List<ProdutoOS> produtosOS;
	
	private List<Item> itensOs;
	
	private List<OS> listaOs; //para listagem da tabela
	
	public List<OS> getListaOs() {
		return listaOs;
	}
	
	public void setListaOs(List<OS> listaOs) {
		this.listaOs = listaOs;
	}
	
	public OS getOsEditar() {
		return osEditar;
	}
	
	public void setOsEditar(OS osEditar) {
		this.osEditar = osEditar;
	}
	
	public List<Item> getItensOs() {
		return itensOs;
	}

	public void setItensOs(List<Item> itensOs) {
		this.itensOs = itensOs;
	}
	
	public List<ProdutoOS> getProdutosOS() {
		return produtosOS;
	}
	
	public void setProdutosOS(List<ProdutoOS> produtosOS) {
		this.produtosOS = produtosOS;
	}
	
	
	
	//--------- metodos -------// 

	@PostConstruct
	public void listarOs() {
		try {
			OsDAO osDAO = new OsDAO();
			listaOs =  osDAO.listarbYDate();
			
			ProdutoOsDAO pdao = new ProdutoOsDAO();
			produtosOS = pdao.listar();
			
			
		}catch (Exception e) {
				Messages.addGlobalError("erro ao listas as ordens de serviço !");
				e.printStackTrace();
			}
	}
	
	
	public void adicionarItem(ActionEvent event) {
	
		ProdutoOS produtoOSselecionado =  (ProdutoOS)event.getComponent()
				.getAttributes().get("produtoSelecionado");
		
		System.out.println(produtoOSselecionado);
		
		int achou = -1;
		for(int posicao = 0; posicao < itensOs.size() ; posicao++) {
			if(itensOs.get(posicao).getProdutoOS().equals(produtoOSselecionado) ) {
				achou = posicao;
			}
		}
		
		if(achou == -1) {
			Item item = new Item();
			item.setProdutoOS(produtoOSselecionado);
			item.setValorParcial(produtoOSselecionado.getValorPorHora());
			item.setQuantidade(1);
			
			itensOs.add(item);
			System.out.println("item adicionado" + item);
			
		}else {
			Item item = itensOs.get(achou);
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorParcial(produtoOSselecionado.getValorPorHora().multiply(new BigDecimal(item.getQuantidade() ))  );
			
			System.out.println("item removido" + item);
		}
		//atualizar o valor total
		calcular();
		
		
	}
	
	
	public void removerItem(ActionEvent event) {
		Item itemSelecionado = (Item)event.getComponent().getAttributes().get("itemSelecionado");
		System.out.println("item removido: " + itemSelecionado);
		
		int achou = -1;
		
		for(int posicao = 0 ; posicao < itensOs.size() ; posicao++) {
			if(itensOs.get(posicao).getProdutoOS().equals(itemSelecionado.getProdutoOS())) {
				achou = posicao;
			}
		}
		
		if(achou > -1) {
			itensOs.remove(achou);
			
		}
		//atualizar o valor total
		calcular();
	}
	
	
	public void calcular() {
		osEditar.setValorTotal(new BigDecimal("0.00"));
		
		//limpa no finalizar
		osEditar.setResponsavelOS(null);
		osEditar.setUsuario(null);
		
		for(int posicao = 0 ; posicao < itensOs.size(); posicao++) {
			Item item = itensOs.get(posicao);
			osEditar.setValorTotal(osEditar.getValorTotal().add(item.getValorParcial())  );
		}
		
	}
	
	
	
	public void editarComParametro(OS os) {
		try {
			//osEditar = new OS();
			osEditar = os;
			System.out.println("OS Para edição selecionada: " + osEditar);
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao editar OS");
			e.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent event) {
		
		try {
			//osEditar = new OS();
			osEditar = (OS)event.getComponent().getAttributes().get("osSelecionada");
			
			
			System.out.println("OS Para edição selecionada: " + osEditar);
			
			//OsDAO osDAO = new OsDAO();
			//osDAO.editar(osSelecionado);
			
			List<Item> itens = osEditar.getItensOs();
			for(Item item : itens) {
				System.out.println(item);
			}
			
			//faz uma copia dos itens da os que vem do banco de dados para o bean, para posterior edicao
			itensOs = new ArrayList<Item>();
			itensOs = itens;
			
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao editar OS");
			e.printStackTrace();
		}
	
		
		
	}
	
}
