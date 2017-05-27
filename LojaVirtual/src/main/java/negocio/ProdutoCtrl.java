package negocio;

import java.io.Serializable;

import javax.faces.bean.*;

import java.util.ArrayList;
import java.util.List;

import persistencia.EstadoDAO;
import persistencia.ProdutoDAO;
import beans.Pessoa;
import beans.Produto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListagem(){
		return ProdutoDAO.listagem("");
	}

	public String actionGravar() {
		if (produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
			return actionInserir();
		} else {
			ProdutoDAO.alterar(produto);
			return "lista_produto";
		}
	}

	public String actionInserir() {
		produto = new Produto();
		return "lista_produto";
		
	}

	public String actionExcluir() {
		ProdutoDAO.excluir(produto);
		return "lista_produto";
	}
	

	public String actionAlterar(Produto p) {
		produto = p;
		return "form_produto";
	}
	
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Produto selecionado",
				String.valueOf(((Produto)event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
