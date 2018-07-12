package br.com.os.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.os.dao.OsDAO;
import br.com.os.dao.ProdutoOsDAO;
import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;
import br.com.os.domain.ResponsavelOS;

@ManagedBean
@ViewScoped
public class OsBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ProdutoOS> produtosOS;
	private List<Item> itensOs;
	private List<ResponsavelOS> responsaveis;
	
	private OS os;
	
	private List<OS> listaOs; //para listagem da tabela
	
	public List<OS> getListaOs() {
		return listaOs;
	}
	
	public void setListaOs(List<OS> listaOs) {
		this.listaOs = listaOs;
	}
	
	
	public List<ProdutoOS> getProdutosOS() {
		return produtosOS;
	}
	
	public void setProdutosOS(List<ProdutoOS> produtosOS) {
		this.produtosOS = produtosOS;
	}
	
	public OS getOs() {
		return os;
	}
	
	public void setOs(OS os) {
		this.os = os;
	}
	
	public List<ResponsavelOS> getResponsaveis() {
		return responsaveis;
	}
	
	public void setResponsaveis(List<ResponsavelOS> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	public List<Item> getItensOs() {
		return itensOs;
	}

	public void setItensOs(List<Item> itensOs) {
		this.itensOs = itensOs;
	}

	@PostConstruct
	public void novo() {
		try {
			os = new OS();
			os.setValorTotal(new BigDecimal("0.00"));
			
			
			ProdutoOsDAO pdao = new ProdutoOsDAO();
			produtosOS = pdao.listar();
			
			itensOs = new ArrayList<Item>();
			//popular lista de os ja salvas para a tabela
			
			OsDAO osDAO = new OsDAO();
			listaOs = osDAO.listar();
			
			
			
		}catch (Exception e) {
			Messages.addGlobalError("erro ao listas os itens");
			e.printStackTrace();
		}
	}
	
	
	public void adicionar(ActionEvent event) {
		ProdutoOS produtoOSselecionado =  (ProdutoOS)event.getComponent()
				.getAttributes().get("produtoSelecionado");
		
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
		}else {
			Item item = itensOs.get(achou);
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorParcial(produtoOSselecionado.getValorPorHora().multiply(new BigDecimal(item.getQuantidade() ))  );
		}
		//atualizar o valor total
		calcular();
		
		
	}
	
	
	public void editar(ActionEvent event) {
		OS osSelecionado = (OS)event.getComponent().getAttributes().get("itemSelecionado");
	}
	
	
	public void remover(ActionEvent event) {
		Item itemSelecionado = (Item)event.getComponent().getAttributes().get("itemSelecionado");
		
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
		os.setValorTotal(new BigDecimal("0.00"));
		
		//limpa no finalizar
		os.setResponsavelOS(null);
		os.setUsuario(null);
		
		for(int posicao = 0 ; posicao < itensOs.size(); posicao++) {
			Item item = itensOs.get(posicao);
			os.setValorTotal(os.getValorTotal().add(item.getValorParcial())  );
		}
		
	}
	
	public void finalizar() {
		try {
			os.setDataLancamento(new Date());
			
			ResponsavelOsDAO rdao = new ResponsavelOsDAO();
			responsaveis = rdao.listar();
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao finalizar a OS");
			e.printStackTrace();
		}
	}
	
	
	public void salvar() {
		try {
			if(os.getValorTotal().signum() == 0 ) {
				
				Messages.addGlobalError("Informe pelo menos um item para a OS.");
				return;
			}
			OsDAO osdao = new OsDAO();
			//osdao.salvar(os);
			osdao.salvar(os, itensOs);
			
			Messages.addGlobalInfo("Ordem de serviço salva com sucesso !");
			
			//limpar tudo
			novo();
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao salvar a OS.");
		}
	}
	

}
