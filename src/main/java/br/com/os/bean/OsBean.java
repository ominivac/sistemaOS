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

import br.com.os.dao.ItemDAO;
import br.com.os.dao.OsDAO;
import br.com.os.dao.ProdutoOsDAO;
import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;
import br.com.os.domain.ResponsavelOS;

@ManagedBean
@ViewScoped
public class OsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	//private OS osEditar;
	private OS ordemServico;
	private List<ProdutoOS> produtosOS;
	private List<ResponsavelOS> responsaveis;

	private List<Item> itensOs;

	private List<OS> listaOs; // para listagem da tabela
	
	
	
	public OS getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OS ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<OS> getListaOs() {
		return listaOs;
	}

	public void setListaOs(List<OS> listaOs) {
		this.listaOs = listaOs;
	}

	

	public List<Item> getItensOs() {
		return itensOs;
	}

	public List<ResponsavelOS> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<ResponsavelOS> responsaveis) {
		this.responsaveis = responsaveis;
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

	public OsBean() {
		
	}


	// ------------------------------ metodos ---------------------------------
	
	@PostConstruct
	public void listarTodos() {
		listarProdutos();
		//listarResponsaveis();
		listarOs();
		
	}

	public void listarProdutos() {
		ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
		produtosOS = produtoDAO.listar();
	}

	public void listarResponsaveis() {
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		responsaveis = rdao.listar();
	}
	
	
	public void listarOs() {
		try {
			OsDAO osDAO = new OsDAO();
			listaOs = osDAO.listarbYDate();

		} catch (Exception e) {
			Messages.addGlobalError("erro ao listas as ordens de serviço !");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		ordemServico = new OS();
		ordemServico.setValorTotal(new BigDecimal("0.00"));
		ordemServico.setDataLancamento(new Date());
		
		//ResponsavelOS responsavel = new ResponsavelOS();
		//ordemServico.setResponsavelOS(responsavel);
		
		itensOs = new ArrayList<Item>();
		ordemServico.setItensOs(itensOs);
		
	}
	
	public void adicionarItem(ActionEvent event) {

		ProdutoOS produtoOSselecionado = (ProdutoOS) event.getComponent().getAttributes().get("produtoSelecionado");
		String action = (String)event.getComponent().getAttributes().get("action");
		
		System.out.println(produtoOSselecionado);
		System.out.println("ação" + action);

		int achou = -1;
		for (int posicao = 0; posicao < itensOs.size(); posicao++) {
			if (itensOs.get(posicao).getProdutoOS().equals(produtoOSselecionado)) {
				achou = posicao;
			}
		}

		if (achou == -1) {
			Item item = new Item();
			item.setProdutoOS(produtoOSselecionado);
			// item.setValorParcial(produtoOSselecionado.getValorPorHora());
			item.setQuantidade(1);
			item.getValorParcial();

			item.setOs(ordemServico);// importante para o update !

			itensOs.add(item);
			System.out.println("item adicionado" + item);

		} else {
			Item item = itensOs.get(achou);
			item.setQuantidade(item.getQuantidade() + 1);
			// item.setValorParcial(produtoOSselecionado.getValorPorHora().multiply(new
			// BigDecimal(item.getQuantidade() )) );
			item.getValorParcial();

			item.setOs(ordemServico);// importante para o update !

			System.out.println("item ja na lista atualizando valor" + item);
		}
		
		System.out.println("tamanho da lista " + itensOs.size() );
		// atualizar o valor total
		calcular();

	}

	public void removerItem(ActionEvent event) {
		Item itemSelecionado = (Item) event.getComponent().getAttributes().get("itemSelecionado");
		System.out.println("item removido: " + itemSelecionado);

		//ItemDAO idao = new ItemDAO();

		int achou = -1;

		for (int posicao = 0; posicao < itensOs.size(); posicao++) {
			if (itensOs.get(posicao).getProdutoOS().getCodigo() == itemSelecionado.getProdutoOS().getCodigo()) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			//Item itemToRemove = idao.buscarPorOsEproduto(ordemServico.getCodigo(), itemSelecionado.getProdutoOS().getCodigo());
			//idao.excluir(itemToRemove);

			itensOs.remove(achou);
		}
		System.out.println("tamanho da lista " + itensOs.size() );
		// atualizar o valor total
		calcular();
	}

	public void calcular() {
		ordemServico.getValorTotal();
	}

	public void finalizar() {
		listarResponsaveis();
	}
	
	
	public void prepararEdicao(ActionEvent event) {

		try {
			// osEditar = new OS();
			ordemServico = (OS) event.getComponent().getAttributes().get("osSelecionada");

			System.out.println("OS Para edição selecionada: " + ordemServico);

			List<Item> itens = ordemServico.getItensOs();
			for (Item item : itens) {
				System.out.println(item);
			}

			// faz uma copia dos itens da os que vem do banco de dados para o bean, para
			// posterior edicao
			itensOs = new ArrayList<Item>();
			itensOs = itens;

			ordemServico.setItensOs(itensOs);

			ResponsavelOsDAO rdao = new ResponsavelOsDAO();
			responsaveis = new ArrayList<ResponsavelOS>();
			responsaveis = rdao.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao preparar edição");
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {
			if(ordemServico.getValorTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um Item para a OS ! ");
				return;
			}
			
			
			OsDAO osdao = new OsDAO();
			osdao.salvar(ordemServico, itensOs);
			
			Messages.addGlobalInfo("Ordem de serviço salva com sucesso !");

			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro salvar a OS! ");
		}
	}
	
	public void editar(ActionEvent event) {
		System.out.println(ordemServico);

		OsDAO osDAO = new OsDAO();
		// pesquisa a os que deseja edicao pelo cod
		// osDAO.buscarPorCodigo(osEditar.getCodigo() );

		try {
			if (ordemServico.getValorTotal().signum() == 0) {

				Messages.addGlobalError("Informe pelo menos um item para a OS.");
				return;
			}

			osDAO.editar(ordemServico);

			Messages.addGlobalInfo("Ordem de serviço editada com sucesso !");

			// limpar tudo
			// novo();
			// osEditar = new OS();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao editar a OS.");
		}
	}

	
	
	
}
