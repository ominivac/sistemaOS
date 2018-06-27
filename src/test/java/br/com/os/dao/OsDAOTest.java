package br.com.os.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.os.domain.OS;
import br.com.os.domain.ResponsavelOS;

public class OsDAOTest {
	
	@Test
	public void salvar() {
		
		
		ResponsavelOS responsavelOS = new ResponsavelOS();
		responsavelOS.setCodigo(1);
		OS os = new OS();
		os.setValorTotal(new BigDecimal("574.00"));
		os.setResponsavelOS(responsavelOS);
		
		
		OsDAO osdao = new OsDAO();
		
		
		osdao.salvar(os);
		
	}
	

}
