package br.com.os.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

import br.com.os.dao.ResponsavelOsDAO;
import br.com.os.domain.ResponsavelOS;

//@FacesConverter("responsavelConverter")
@FacesConverter(forClass = ResponsavelOS.class)
public class ResponsavelConverter implements Converter {

	
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		System.out.println("getAsObject " + valor);
		try {
			Integer codigo = Integer.parseInt(valor);
			ResponsavelOsDAO rdao = new ResponsavelOsDAO();
			ResponsavelOS responsavelOS = rdao.buscarPorCodigo(codigo);
			return responsavelOS;
			
		}catch (RuntimeException e) {
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component, Object objeto)throws ConverterException {
		System.out.println("getAsObject " + objeto);
		try {
			ResponsavelOS responsavelOS = (ResponsavelOS)objeto;
			Integer codigo = responsavelOS.getCodigo();
			return codigo.toString();
			
			
		}catch (RuntimeException e) {
			return null;
		}
		
	}
	
	

}
