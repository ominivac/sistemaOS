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
import br.com.os.dao.ProdutoOSDAO;
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
	private List<Item> itensOs ;
	private List<ResponsavelOS> responsaveis = new ArrayList<ResponsavelOS>();
	
	private OS os;
	private Item item;
	
	private ResponsavelOS responsavelOS ;
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public List<ProdutoOS> getProdutosOS() {
		return produtosOS;
	}

	public void setProdutosOS(List<ProdutoOS> produtosOS) {
		this.produtosOS = produtosOS;
	}
	
	public List<Item> getItensOs() {
		return itensOs;
	}
	
	public void setItensOs(List<Item> itensOs) {
		this.itensOs = itensOs;
	}
	
	public ResponsavelOS getResponsavelOS() {
		return responsavelOS;
	}
	
	
	public void setResponsavelOS(ResponsavelOS responsavelOS) {
		this.responsavelOS = responsavelOS;
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

	public void novo() {
		os = new OS();
	}
	
	@PostConstruct
	public void nova() {
		try {
			os = new OS();
			os.setValorTotal(new BigDecimal("0.00"));
			
			
			
			
			ProdutoOSDAO pdao = new ProdutoOSDAO();
			produtosOS = pdao.listar();
			
			itensOs = new ArrayList<Item>();
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao listar os itens de OS");
			e.printStackTrace();
		}
	}
	
	public void adicionar(ActionEvent evento) {
		ProdutoOS produto =  (ProdutoOS) evento.getComponent().getAttributes().get("produtoSelecionado");
		System.out.println(produto.getDescricao() );
		
		int achou = -1;
		for(int posicao = 0; posicao < itensOs.size() ; posicao++) {
			if(itensOs.get(posicao).getProdutoOS().equals(produto) ) {
				achou = posicao;
			}
		}
		
		if(achou < 0) {
			Item item = new Item();
			item.setProdutoOS(produto);
			item.setValorParcial(produto.getValorPorHora() );
			item.setQuantidade(1);
			
			itensOs.add(item);
		}else {
			Item item = itensOs.get(achou);
			item.setQuantidade(item.getQuantidade() +1 );
			item.setValorParcial(produto.getValorPorHora().multiply(new BigDecimal(item.getQuantidade())));
		}
			
		calcular();
	}
	
	
	public void remover(ActionEvent evento) {
		Item itemSelecionado =  (Item) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for(int posicao = 0 ; posicao < itensOs.size() ; posicao++) {
			if(itensOs.get(posicao).getProdutoOS().equals(itemSelecionado.getProdutoOS()) ) {
				achou  = posicao;
			}
		}
		
		if(achou > -1) {
			itensOs.remove(achou);
		}
		calcular();
	}
	
	public void calcular() {
		os.setValorTotal(new BigDecimal("0.00"));
		
		for(int pos = 0; pos < itensOs.size() ; pos++) {
			Item i = itensOs.get(pos);
			os.setValorTotal( os.getValorTotal().add(i.getValorParcial()) );
		}
	}
	
	public void finalizar() {
		try {
			ResponsavelOsDAO rdao = new ResponsavelOsDAO();
			responsaveis = rdao.listar();
			
			responsavelOS = new ResponsavelOS();
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao finalizar a OS");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			if(os.getValorTotal().signum() == 0 ) {
				Messages.addGlobalError("A OS deve conter ao menos um item!");
				return;
			}
			
			
			
			os.setResponsavelOS(responsavelOS);
			
			OsDAO osdao = new OsDAO();
			osdao.save(os, itensOs);
			
			novo();
			
			Messages.addGlobalInfo("OS salva com sucesso !");
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao finalizar a OS");
			e.printStackTrace();
		}
	}
	
	
	

}
