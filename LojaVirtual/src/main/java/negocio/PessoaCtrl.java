package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistencia.CidadeDAO;
import persistencia.EstadoDAO;
import persistencia.PessoaDAO;
import beans.Cidade;
import beans.End_Estado;
import beans.Fone;
import beans.Pessoa;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Fone fone;
	private End_Estado end_Estado;
	private Cidade cidade;

	private List<End_Estado> end_Estados;
	private List<Cidade> cidades;

	// Métodos de ações e controles

	public List<Pessoa> getListagem1() {
		return PessoaDAO.listagem1("");
	}

	public String actionGravar() {

		try {
			if (pessoa.getId() == 0) {
				
				
				pessoa.setUf(end_Estado.getSigla());
				pessoa.setCidade(cidade.getNome());
				
				System.out.println("Pessoa UF: " + pessoa.getUf());
				System.out.println("Pessoa Cidade: " + pessoa.getCidade());
				
				PessoaDAO.inserir(pessoa);
				return actionInserir();
			} else {
				PessoaDAO.alterar(pessoa);
				return "lista_pessoa";
			}

		} catch (RuntimeException erro) {
			System.out.println("Erro ao tentar gravar uma nova pessoa.");
			erro.printStackTrace();

			return null;
		}
	}

	public String actionInserir() {

		try {
			pessoa = new Pessoa();

			EstadoDAO estadodao = new EstadoDAO();
			end_Estados = estadodao.listagem();

			cidades = new ArrayList<>();

			return "lista_pessoa";
		} catch (RuntimeException erro) {
			System.out.println("Erro ao tentar carregar Estados ao abrir o formulário de pessoa.");
			erro.printStackTrace();
			return null;
		}
	}

	public String actionExcluir() {
		PessoaDAO.excluir(pessoa);
		return "lista_pessoa";
	}

	public String actionAlterar(Pessoa p) {
		pessoa = p;
		return "form_pessoa";
	}

	public String actionInserirFone() {
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		return "form_pessoa";
	}

	public String actionExcluirFone(Fone f) {
		// Os alunos ... pesquisar e codificar

		PessoaDAO.excluirFone(f);
		pessoa.getFones().remove(f);
		return "form_pessoa";
	}

	public void popularCidade() {
		try {
			if (end_Estado != null) {
				CidadeDAO cidadedao = new CidadeDAO();
				cidades = cidadedao.buscaPorEstado(end_Estado.getId());
			} else {
				cidades = new ArrayList<>(); //Carregar uma lista vazia de cidades
			}
		} catch (RuntimeException erro) {
			System.out.println("Combo Cidade não pode ser carregada.");
			erro.printStackTrace();
		}
	}

	// Métodos Getters e Setters

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Fone getFone() {
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

	public End_Estado getEnd_Estado() {
		return end_Estado;
	}

	public void setEnd_Estado(End_Estado end_Estado) {
		this.end_Estado = end_Estado;
	}

	public List<End_Estado> getEnd_Estados() {
		return end_Estados;
	}

	public void setEnd_Estados(List<End_Estado> end_Estados) {
		this.end_Estados = end_Estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
