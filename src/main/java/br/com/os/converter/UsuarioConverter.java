package br.com.os.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

import br.com.os.dao.UsuarioDAO;

import br.com.os.domain.Usuario;

//@FacesConverter("responsavelConverter")
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		System.out.println("getAsObject " + valor);
		try {
			Integer codigo = Integer.parseInt(valor);
			UsuarioDAO udao = new UsuarioDAO();
			Usuario usuario = udao.buscarPorCodigo(codigo);
			return usuario;
			
		}catch (RuntimeException e) {
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component, Object objeto)throws ConverterException {
		System.out.println("getAsObject " + objeto);
		try {
			Usuario usuario = (Usuario)objeto;
			Integer codigo = usuario.getCodigo();
			return codigo.toString();
			
			
		}catch (RuntimeException e) {
			return null;
		}
		
	}
	
	

}
