package br.com.os.bean;

import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.os.dao.UsuarioDAO;
import br.com.os.domain.Usuario;
import br.com.os.util.SendEmail;

@ManagedBean
@ViewScoped
public class ResetEmailBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailContato = "";
	
	public String getEmailContato() {
		return emailContato;
	}
	
	public void setEmailContato(String email) {
		this.emailContato = email;
	}
	
	
	private String gerarNovaSenha() {
        String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                      "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                      "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                      "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                      "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                      "W", "X", "Y", "Z" };

        String senha = "";

        for (int x = 0; x < 10; x++) {
               int j = (int) (Math.random() * carct.length);
               senha += carct[j];

        }

        return senha;
	}
	
	
	
	
	public void aplicarEmail() {	
		System.out.println("email para enviar nova senha " + emailContato);
	}
	
	public void resetarSenha() {
		
		
		String newSenha =  gerarNovaSenha();
		UsuarioDAO udao = new UsuarioDAO();
		Usuario upesquisado = udao.buscarPorEmail(emailContato);
		
		if(upesquisado == null) {
			Messages.addGlobalError("Email não cadastrado na base de dados !" );
		}else {
			upesquisado.setSenhaSemCripto(newSenha);
			SimpleHash hash = new SimpleHash("md5", upesquisado.getSenhaSemCripto());
			upesquisado.setSenha(hash.toHex() );
			
			udao.merge(upesquisado);
			SendEmail sendEmail = new SendEmail(emailContato, newSenha);
			sendEmail.enviarEmail();
			
			Messages.addGlobalInfo("Uma nova senha foi gerada para o email informado" );
			
		}
		
		
		
	}
	
	
	

}
