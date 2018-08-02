package br.com.os.util;



import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.os.bean.AutenticacaoBean;
import br.com.os.domain.Usuario;

public class AutenticacaoListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		String paginaAtual =  Faces.getViewId();
		System.out.println("pagina atual: " + paginaAtual);
		
		boolean ehPaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		if(!ehPaginaAutenticacao) {
			//eh pagina privada, chechar se esta logado
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			System.out.println("AutenticacaoBean: " + autenticacaoBean);
			
			if(autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			Usuario ulogado = autenticacaoBean.getUsuarioLogado();
			
			if(ulogado == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
			
	}
	
	
	

	public void beforePhase(PhaseEvent event) {
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
