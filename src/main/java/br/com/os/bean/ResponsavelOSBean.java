package br.com.os.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.domain.ResponsavelOS;

@ManagedBean
@ViewScoped
public class ResponsavelOSBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private ResponsavelOS responsavelOS;
	private List<ResponsavelOS> responsaveis;
	
	public ResponsavelOS getResponsavelOS() {
		return responsavelOS;
	}
	
	public void setResponsavelOS(ResponsavelOS responsavelOS) {
		this.responsavelOS = responsavelOS;
	}

	public List<ResponsavelOS> getResponsaveis() {
		return responsaveis;
	}
	
	public void setResponsaveis(List<ResponsavelOS> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	
	public void novo() {
		responsavelOS = new ResponsavelOS();
	}
	
	@PostConstruct
	public void listar() {
		try {
			ResponsavelOsDAO rdao = new ResponsavelOsDAO();
			responsaveis = rdao.listar();
			
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro listar os responsáveis");
			e.printStackTrace();
		}
	}
	
	
	public void salvar() {
		try {
		
		ResponsavelOsDAO responsavelOsDAO = new ResponsavelOsDAO();
		responsavelOsDAO.salvar(responsavelOS);
		
		responsavelOS = new ResponsavelOS();
		responsaveis = responsavelOsDAO.listar();
		
		Messages.addGlobalInfo("Responsável salvo com sucesso");
		
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar responsável");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		ResponsavelOS respSelec = new ResponsavelOS();
		respSelec = (ResponsavelOS) evento.getComponent().getAttributes().get("respSelecionado");
		Messages.addGlobalInfo("nome "+ respSelec.getCodigo() + " " + respSelec.getNome() );
	}
	
	
	
}
