package br.com.os.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.os.dao.ItemDAO;
import br.com.os.dao.OsDAO;
import br.com.os.dao.ProdutoOsDAO;
import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.dao.UsuarioDAO;
import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.domain.ProdutoOS;
import br.com.os.domain.ResponsavelOS;
import br.com.os.domain.Usuario;
import br.com.os.filter.OSFilter;
import br.com.os.util.HibernateUtil;
import net.sf.jasperreports.engine.JasperRunManager;

@ManagedBean
@ViewScoped
public class OsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OS ordemServico;
	private OS ordemServicoPesquisa;
	
	private OS ordemServicoSelecionada;
	private List<OS> listaOrdemServicoSelecionadas;
	
	
	private List<ProdutoOS> produtosOS;
	private List<ResponsavelOS> responsaveis;
	private List<Usuario> usuarios;

	private List<Item> itensOs;
	private List<OS> listaOs; // para listagem da tabela
	private Item itemCrudEdit; 
	
	private List<OS> listaOsFiltradas;
	private OSFilter osfilter; 
	
	private Date dataInicialPesquisa;
	private Date dataFinalPesquisa;
	
	
	private Integer tipoPesquisa;
	private Boolean todasOsSelecionadas;
	
	
	
	
	public OS getOrdemServicoSelecionada() {
		return ordemServicoSelecionada;
	}

	public void setOrdemServicoSelecionada(OS ordemServicoSelecionada) {
		this.ordemServicoSelecionada = ordemServicoSelecionada;
	}

	public List<OS> getListaOrdemServicoSelecionadas() {
		return listaOrdemServicoSelecionadas;
	}

	public void setListaOrdemServicoSelecionadas(List<OS> listaOrdemServicoSelecionadas) {
		this.listaOrdemServicoSelecionadas = listaOrdemServicoSelecionadas;
	}
	
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("OS Selecionada", ((OS) event.getObject()).getCodigo().toString() );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("OS De-selecionada", ((OS) event.getObject()).getCodigo().toString()  );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	

	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	
	public Boolean getTodasOsSelecionadas() {
		return todasOsSelecionadas;
	}

	public void setTodasOsSelecionadas(Boolean todasOsSelecionadas) {
		this.todasOsSelecionadas = todasOsSelecionadas;
	}

	public OS getOrdemServicoPesquisa() {
		return ordemServicoPesquisa;
	}

	public void setOrdemServicoPesquisa(OS ordemServicoPesquisa) {
		this.ordemServicoPesquisa = ordemServicoPesquisa;
	}

	public Date getDataInicialPesquisa() {
		return dataInicialPesquisa;
	}

	public void setDataInicialPesquisa(Date dataInicialPesquisa) {
		this.dataInicialPesquisa = dataInicialPesquisa;
	}

	public Date getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}

	public void setDataFinalPesquisa(Date dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}


	private ProdutoOS produtoOSselecionado;
	private ProdutoOS produtoOSselecionadoEditar;
	
	
	public OSFilter getOsfilter() {
		return osfilter;
	}
	
	public void setOsfilter(OSFilter osfilter) {
		this.osfilter = osfilter;
	}

	public Item getItemCrudEdit() {
		return itemCrudEdit;
	}

	public void setItemCrudEdit(Item itemCrudEdit) {
		this.itemCrudEdit = itemCrudEdit;
	}

	public ProdutoOS getProdutoOSselecionado() {
		return produtoOSselecionado;
	}

	public void setProdutoOSselecionado(ProdutoOS produtoOSselecionado) {
		this.produtoOSselecionado = produtoOSselecionado;
	}
	
	
	public ProdutoOS getProdutoOSselecionadoEditar() {
		return produtoOSselecionadoEditar;
	}

	public void setProdutoOSselecionadoEditar(ProdutoOS produtoOSselecionadoEditar) {
		this.produtoOSselecionadoEditar = produtoOSselecionadoEditar;
	}

	public List<OS> getListaOsFiltradas() {
		return listaOsFiltradas;
	}
	
	public void setListaOsFiltradas(List<OS> listaOsFiltradas) {
		this.listaOsFiltradas = listaOsFiltradas;
	}
	


	
	
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
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	public void gerarRelatorioSelecionadas(ActionEvent evento) {
		System.out.println("entrou gerarRelatorioSelecionadas");
		
		for(int i=0; i< listaOrdemServicoSelecionadas.size() ; i++) {
			OS osImprimir = listaOrdemServicoSelecionadas.get(i);
			System.out.println("os para impressao:" + osImprimir);
		}
		
		Queue<OS> filaImpressao = new LinkedList<OS>();
		for(int i=0; i< listaOrdemServicoSelecionadas.size() ; i++) {
			filaImpressao.add(listaOrdemServicoSelecionadas.get(i));
		}

		
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();
		
		String contextpath = context.getContextPath();
		System.out.println(contextpath);
		
		String arquivo = context.getRealPath("/reports/relatorio_os_a4_uma_os2_tabela2_v3.jasper");
		System.out.println(arquivo);

		for(int i=0; i< filaImpressao.size() ; i++) {
			OS osImprimir = filaImpressao.poll();
			System.out.println("os da fila impressao:" + osImprimir);
			gerarRelatorioWeb(arquivo, osImprimir);
		}
		
		//
		
		
	}
	
	public void gerarRelatorio(ActionEvent evento) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();
		
		String contextpath = context.getContextPath();
		System.out.println(contextpath);
		
		String arquivo = context.getRealPath("/reports/relatorio_os_a4_uma_os2_tabela2_v3.jasper");
		System.out.println(arquivo);

		OS osImprimir = (OS) evento.getComponent().getAttributes().get("osSelecionada");
		System.out.println("os para impressao:" + osImprimir);
		
		
		
		gerarRelatorioWeb(arquivo, osImprimir);
	}
	
	private void gerarRelatorioWeb(String arquivo, OS osToPrint) {
		ServletOutputStream servletOutputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			servletOutputStream = response.getOutputStream();
			Connection conexao = HibernateUtil.getConexao();
			Map<String, Object> parametros = new HashMap<String, Object>();
			System.out.println("codigo os to print" + osToPrint.getCodigo() );
			
			parametros.put("cod_os", osToPrint.getCodigo() );
			
			

			// JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)),
			// response.getOutputStream(), parametros, conexao);
			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(),
					parametros, conexao);

			response.setContentType("application/pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
			context.renderResponse();
			context.responseComplete();
			System.out.println("entrou gerar rel na web");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@PostConstruct
	public void listarTodos() {
		listarProdutos();
		//listarResponsaveis();
		listarOs();
		osfilter = new OSFilter();
		dataInicialPesquisa = new Date();
		dataFinalPesquisa = new Date();
		ordemServicoPesquisa = new OS();
	}
	
	public void handleDateSelect(SelectEvent event) {
		 System.out.println((Date)event.getObject() );
	}
	
	
	public void listarPorCodigo() {
		try{
			OsDAO osdao = new OsDAO();
			//ordemServicoPesquisa = new OS();
			listaOsFiltradas = new ArrayList<OS>();
			
			
			
			if(tipoPesquisa == 1) {
				System.out.println("tipo pesquisa por codigo");
				ordemServicoPesquisa  = osdao.buscarPorCodigo(ordemServicoPesquisa.getCodigo() );
				System.out.println(ordemServicoPesquisa);
				listaOsFiltradas.add(ordemServicoPesquisa);
				
			}
			
			
		}catch (Exception e) {
			Messages.addGlobalError("erro pesquisar por c�digo da OS !");
			e.printStackTrace();
		}
	}
	
	public void listarPorAtividade() {
		try{
			OsDAO osdao = new OsDAO();
			//ordemServicoPesquisa = new OS();
			listaOsFiltradas = new ArrayList<OS>();
			
			if(tipoPesquisa == 2) {
				System.out.println("tipo pesquisa por atividade");
				
				listaOsFiltradas  = osdao.buscarPorAtividade(ordemServicoPesquisa.getAtividade() );
				System.out.println(listaOsFiltradas);
				
			}
		}catch (Exception e) {
			Messages.addGlobalError("erro pesquisar por c�digo da OS !");
			e.printStackTrace();
		}
	}
	
	
	
	public void listarPorIntervaloData() {
		//System.out.println("entrou no metodo");
		try {
			OsDAO osdao = new OsDAO();
			
			
			//String data_inicial = "1/1/2018";
			//String data_final = "31/12/2018";
			if(osfilter != null) {
				String data_inicial = dataInicialPesquisa.toString();
				String data_final = dataFinalPesquisa.toString();
				
				SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
				
				String dataFormatIni = formatado.format(dataInicialPesquisa);
				String dataFormatFim = formatado.format(dataFinalPesquisa);
				
				System.out.println(dataFormatIni + " " + dataFormatFim);
				
				listaOsFiltradas = osdao.buscarEntreDatas(dataFormatIni, dataFormatFim);
			}
			
		}catch (Exception e) {
			Messages.addGlobalError("erro pesquisar por data !");
			e.printStackTrace();
		}
		
	}

	public void listarProdutos() {
		ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
		produtosOS = produtoDAO.listar();
	}

	public void listarResponsaveis() {
		ResponsavelOsDAO rdao = new ResponsavelOsDAO();
		responsaveis = rdao.listar();
	}
	
	public void listarUsuarios() {
		UsuarioDAO udao = new UsuarioDAO();
		usuarios =  udao.listar();
	}
	
	
	public void listarOs() {
		try {
			OsDAO osDAO = new OsDAO();
			listaOs = osDAO.listarbYDate();

		} catch (Exception e) {
			Messages.addGlobalError("erro ao listas as ordens de servi�o !");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		
		
		ordemServico = new OS();
		ordemServico.setValorTotal(new BigDecimal("0.00"));
		ordemServico.setDataSolicitacao(new Date() );
		ordemServico.setAberta(true);
		
		//ResponsavelOS responsavel = new ResponsavelOS();
		//ordemServico.setResponsavelOS(responsavel);
		itemCrudEdit = new Item();
		
		produtoOSselecionado = new ProdutoOS();
		
		
		itensOs = new ArrayList<Item>();
		ordemServico.setItensOs(itensOs);
		
	}
	
	public void prepararItemNovo(ActionEvent event) {
		produtoOSselecionado = (ProdutoOS) event.getComponent().getAttributes().get("produtoSelecionado");
		System.out.println(produtoOSselecionado);
		
		itemCrudEdit = new Item();
		
		itemCrudEdit.setQuantidadeHoras(0);
		itemCrudEdit.setQuantidade(0);
		itemCrudEdit.setAberto(true);
		
		itemCrudEdit.setProdutoOS(produtoOSselecionado);
		

	}
	
	public void prepararEdicaoItem(ActionEvent event) {
		//Item itemSelecionadoEditar = (Item) event.getComponent().getAttributes().get("itemSelecionado");
		itemCrudEdit = (Item) event.getComponent().getAttributes().get("itemSelecionado");
		System.out.println("item que vem da tela: " + itemCrudEdit);
		
		ordemServico = itemCrudEdit.getOs();
		
		//ItemDAO idao = new ItemDAO();
		//System.out.println("item para editar antes: " + itemSelecionadoEditar);
		System.out.println("Da os para editar: " + ordemServico);
		
		//Item itemToEdit = idao.buscarPorOsEproduto(osParaEdicao.getCodigo(), itemSelecionadoEditar.getCodigo() );
		//System.out.println("Da os para editar depois : " + itemToEdit);
		
	}
	
	
	
	
	public void adicionarItemNovoVersao2() {
		System.out.println(produtoOSselecionado);
		System.out.println(itemCrudEdit);
		
		itemCrudEdit.setProdutoOS(produtoOSselecionado);
		
		itemCrudEdit.setQuantidade(1);
		
		//item.getValorParcial();
		itemCrudEdit.getValorParcial();

		//item.setOs(ordemServico);// importante para o update !
		itemCrudEdit.setOs(ordemServico);

		//itensOs.add(item);
		itensOs.add(itemCrudEdit);
		
		System.out.println("item adicionado" + itemCrudEdit);
	}
	
	public void adicionarItemNovo(ActionEvent event) {

		//ProdutoOS produtoOSselecionado = (ProdutoOS) event.getComponent().getAttributes().get("produtoSelecionado");
		
		System.out.println(produtoOSselecionado);
		System.out.println(itemCrudEdit);
		
		itemCrudEdit.setProdutoOS(produtoOSselecionado);

		int achou = -1;
		for (int posicao = 0; posicao < itensOs.size(); posicao++) {
			if (itensOs.get(posicao).getProdutoOS().equals(produtoOSselecionado)) {
				achou = posicao;
			}
		}

		if (achou == -1) {
			
			//itemCrudEdit.setProdutoOS(produtoOSselecionado);
			
			//item.setProdutoOS(produtoOSselecionado);
			// item.setValorParcial(produtoOSselecionado.getValorPorHora());
			
			//item.setQuantidade(1);
			itemCrudEdit.setQuantidade(1);
			
			//item.getValorParcial();
			itemCrudEdit.getValorParcial();

			//item.setOs(ordemServico);// importante para o update !
			itemCrudEdit.setOs(ordemServico);

			//itensOs.add(item);
			itensOs.add(itemCrudEdit);
			
			System.out.println("item adicionado" + itemCrudEdit);

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
	
	public void adicionarItem(ActionEvent event) {

		ProdutoOS produtoOSselecionado = (ProdutoOS) event.getComponent().getAttributes().get("produtoSelecionado");
		String action = (String)event.getComponent().getAttributes().get("action");
		
		System.out.println(produtoOSselecionado);
		System.out.println("a��o" + action);

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
	
	
	public void editarItem(ActionEvent event) {
		//Item itemSelecionado = (Item) event.getComponent().getAttributes().get("itemSelecionado");
		System.out.println("item para editar: " + itemCrudEdit);
		ItemDAO idao = new ItemDAO();
		
		try {
			int achou = -1;

			for (int posicao = 0; posicao < itensOs.size(); posicao++) {
				if (itensOs.get(posicao).getProdutoOS().getCodigo() == itemCrudEdit.getProdutoOS().getCodigo()) {
					achou = posicao;
				}
			}
			
			if (achou > -1) {
				
				//Item itemToEdit = idao.buscarPorOsEproduto(ordemServico.getCodigo(), itemCrudEdit.getProdutoOS().getCodigo());
				System.out.println("achou item na lista de itens");
				idao.update(itemCrudEdit);
				calcular();
			}
			
			
			
			Messages.addGlobalInfo("Item da OS editado com sucesso !");
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro editar o item da OS!");
			e.printStackTrace();
		}
		
		

	}

	public void removerItem(ActionEvent event) {
		itemCrudEdit = (Item) event.getComponent().getAttributes().get("itemSelecionado");
		System.out.println("item a ser removido: " + itemCrudEdit);
		

		int achou = -1;

		for (int posicao = 0; posicao < itensOs.size(); posicao++) {
			if (itensOs.get(posicao).getProdutoOS().getCodigo() == itemCrudEdit.getProdutoOS().getCodigo()) {
				achou = posicao;
				System.out.println("achou item a ser removido");
			}
		}

		if (achou > -1) {
			itensOs.remove(achou);
			ItemDAO idao = new ItemDAO();
			idao.excluir(itemCrudEdit);
			
			
			System.out.println(itensOs);
			
			ordemServico.setItensOs(itensOs);
			Messages.addGlobalInfo("Item removido com sucesso !");
		}
		System.out.println("tamanho da lista " + itensOs.size() );
		// atualizar o valor total
		calcular();
	}

	public void calcular() {
		
		ordemServico.getValorTotal();
	}
	
	

	public void finalizar() {
		listarUsuarios();
	}
	
	public void prepararEdicaoNovo(ActionEvent event) {
		try {
			
			
			ordemServico = (OS) event.getComponent().getAttributes().get("osSelecionada");
			System.out.println("OS Para edi��o selecionada: " + ordemServico);
			
			System.out.println(ordemServico.getItensOs() );
			
			itensOs = ordemServico.getItensOs();
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao preparar edi��o");
			e.printStackTrace();
		}
	}
	
	public void prepararEdicao(ActionEvent event) {

		try {
			// osEditar = new OS();
			ordemServico = (OS) event.getComponent().getAttributes().get("osSelecionada");

			System.out.println("OS Para edi��o selecionada: " + ordemServico);

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
			Messages.addGlobalError("Erro ao preparar edi��o");
			e.printStackTrace();
		}

	}

	public void salvar() {
		System.out.println("entrou salvar");
		
		try {
			if(ordemServico.getValorTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um Item para a OS ! ");
				return;
			}
			
			System.out.println("itens dentro de salvar"+ itensOs);
			
			OsDAO osdao = new OsDAO();
			
			
			if(ordemServico.getCodigo() == null) {
				//eh uma os nova entao salva
				osdao.salvar(ordemServico, itensOs);
				listaOs = osdao.listar();
				
				Messages.addGlobalInfo("Nova Ordem de servi�o salva com sucesso !");
			}else {
				//eh uma os para edicao, entao edita
				
				//casos os itens da OS tenham sido alterados
				ordemServico.setItensOs(itensOs);
				
				osdao.editar(ordemServico);
				
				
				listaOs = osdao.listar();
				Messages.addGlobalInfo("Ordem de servi�o editada com sucesso !");
			}
			
			
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
			listaOs = osDAO.listar();

			Messages.addGlobalInfo("Ordem de servi�o editada com sucesso !");

			

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao editar a OS.");
		}
	}

	
	
	
}
