package br.com.os.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.os.dao.ProdutoOsDAO;

import br.com.os.domain.ProdutoOS;
import br.com.os.util.GeradorRelatorio;
import br.com.os.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;


@ManagedBean
@ViewScoped
public class ProdutoOSBean  implements Serializable {

	/**
	 *  ver http://javasemcafe.blogspot.com/2011/05/jasperreports-401-javabean-datasource.html
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
	
	
	 public void gerarRelatorio() {
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        ServletContext context = (ServletContext) externalContext.getContext();
	        String arquivo = context.getRealPath("/reports/itens_os.jasper");
	        System.out.println(arquivo);
	 
	      
	 
	        gerarRelatorioWeb(arquivo);
	    }
	 
	    private void gerarRelatorioWeb( String arquivo) {
	        ServletOutputStream servletOutputStream = null;
	        FacesContext context = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
	 
	        try {
	            servletOutputStream = response.getOutputStream();
	            Connection conexao = HibernateUtil.getConexao();
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            
	           // JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, conexao);
	            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, conexao);
	            
	            response.setContentType("application/pdf");
	            servletOutputStream.flush();
	            servletOutputStream.close();
	            context.renderResponse();
	            context.responseComplete();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	public void print() throws IOException {
		try {
			String jrxml = Faces.getRealPath("/reports/itens_os.jrxml");
			String jasper = JasperCompileManager.compileReportToFile(jrxml);
			
			System.out.println("jasper gerado: " + jasper);
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			Connection conexao = HibernateUtil.getConexao();
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response  =  (HttpServletResponse) context.getExternalContext().getResponse(); // obtém o response do seu framework mvc
			OutputStream saida = response.getOutputStream();
			
			GeradorRelatorio gerador = new GeradorRelatorio(conexao);
			gerador.geraPdf("/pages/", parametros, saida);
			
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void imprimir() {
		try {
			//String caminho = Faces.getRealPath("/reports/itens_os.jasper");
			
			//String jrxml = "/reports/itens_os.jrxml";
			String jrxml = Faces.getRealPath("/reports/itens_os.jrxml");
			String jasper = JasperCompileManager.compileReportToFile(jrxml);
			
			
			System.out.println("caminho rel." + jasper);
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			Connection conexao = HibernateUtil.getConexao();
			
			OutputStream saida = new FileOutputStream("itens.pdf");
			
			
			//relatorio ja é o rel. populado
			JasperPrint relatorio = JasperFillManager.fillReport(jasper, parametros, conexao);
			
			
			//JasperPrintManager.printReport(relatorio, true); //true manda para o control+p do SO. podendo salvar
			
			
			
		}catch (JRException ex) {
			Messages.addGlobalError("Erro tentar gerar impressão das Retrancas");
			ex.printStackTrace();
		}
		
		catch (RuntimeException e) {
			Messages.addGlobalError("Erro de conexao na geração do relatorio");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Messages.addGlobalError("Arquivo do relatório não encontrado");
			e.printStackTrace();
		}
	}
	
	
	public void salvar() {
		try {
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.merge(produtoOS);
			
			produtoOS = new ProdutoOS();
			produtosOS = produtoDAO.listar();
			
			
			Messages.addGlobalInfo("Retranca salva com sucesso !");
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Retranca !");
			e.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
			produtoOS = (ProdutoOS) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.excluir(produtoOS);
			
			produtosOS = produtoDAO.listar();
			
			Messages.addGlobalInfo("Retranca editada com sucesso !");

		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro editar Retranca !");
			e.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento) {
		try {
			produtoOS = (ProdutoOS) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoOsDAO produtoDAO = new ProdutoOsDAO();
			produtoDAO.merge(produtoOS);
			produtosOS = produtoDAO.listar();
			
			Messages.addGlobalInfo("Retranca editada com sucesso !");
			
			//Messages.addGlobalInfo("resp selecionado: " + responsavelOS.getNome());
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao editar Retranca !");
			e.printStackTrace();
		}
	}
	
	
	
}
