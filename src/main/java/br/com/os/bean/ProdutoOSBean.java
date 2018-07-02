package br.com.os.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.os.dao.ProdutoOsDAO;
import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.domain.ProdutoOS;
import br.com.os.domain.ResponsavelOS;


@ManagedBean
@ViewScoped
public class ProdutoOSBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProdutoOS produtoOS;
	private List<ProdutoOS> produtosOS;
	
	
	public ProdutoOS getProdutoOS() {
		return produtoOS;
	}
	
	public void setProdutoOS(ProdutoOS produtoOS) {
		this.produtoOS = produtoOS;
	}
	
	
	
	public List<ProdutoOS> getProdutosOS() {
		return produtosOS;
	}
	
	public void setProdutosOS(List<ProdutoOS> produtosOS) {
		this.produtosOS = produtosOS;
	}
	
	public void novo() {
		produtoOS = new ProdutoOS();
	}
	
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtosOS = produtoDAO.listar();
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro listar os responsáveis");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.merge(produtoOS);
			
			produtoOS = new ProdutoOS();
			produtosOS = produtoDAO.listar();
			
			
			Messages.addGlobalInfo("Item de OS salvo com sucesso");
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Item de OS");
			e.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
			produtoOS = (ProdutoOS) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.excluir(produtoOS);
			
			produtosOS = produtoDAO.listar();
			
			Messages.addGlobalInfo("Item de OS excluído com sucesso");

		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar item de OS");
			e.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento) {
		try {
			produtoOS = (ProdutoOS) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.merge(produtoOS);
			produtosOS = produtoDAO.listar();
			
			Messages.addGlobalInfo("Item de OS editado com sucesso");
			
			//Messages.addGlobalInfo("resp selecionado: " + responsavelOS.getNome());
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao editar item de OS");
			e.printStackTrace();
		}
	}
	
	
	
}
