package br.com.os.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

@ManagedBean
public class UsuarioBean {

	public void salvar() {
		String msg = "programacao";
		
		//FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		//FacesContext contexto = FacesContext.getCurrentInstance();
		//contexto.addMessage(null, message);
		
		Messages.addGlobalInfo(msg);
		
	}
	
	
}
