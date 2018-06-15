package br.com.os.bean;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.os.dao.ProdutoOSDAO;
import br.com.os.domain.ProdutoOS;

public class ProdutoOSBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProdutoOS produtoOS;
	
	public ProdutoOS getProdutoOS() {
		return produtoOS;
	}
	
	public void setProdutoOS(ProdutoOS produtoOS) {
		this.produtoOS = produtoOS;
	}
	
	public void novo() {
		produtoOS = new ProdutoOS();
	}
	
	
	public void salvar() {
		ProdutoOSDAO produtoOSDAO = new ProdutoOSDAO();
		produtoOSDAO.salvar(produtoOS);
		
		Messages.addGlobalInfo("Usuário salvo com sucesso");
	}
	
}
