package br.com.os.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.os.bean.AutenticacaoBean;
import br.com.os.domain.Usuario;

public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual =  Faces.getViewId();
		System.out.println("página atual" + paginaAtual);
		
		boolean ehpagdeautenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		if(! ehpagdeautenticacao) {
			AutenticacaoBean autenticacaBean =  Faces.getSessionAttribute("autenticacaoBean");
			System.out.println("autenticacao bean " + autenticacaBean);
			
			if(autenticacaBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaBean.getUsuarioLogado();
			if(usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
