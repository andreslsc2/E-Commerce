package persistencia;

import java.io.Serializable;

import org.hibernate.*;

import java.util.List;

import beans.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static List<Produto> listagem;

	public static void inserir(Produto produto) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(produto);
		t.commit();
		sessao.close();
	}

	public static void alterar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();

	}

	public static void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
	}

	public static List<Produto> listagem(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Produto order by prod_nome ");
		}
		else{
			consulta = sessao.createQuery("from Produto "
					+ "where pro_nome like :paramentro order by prod_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Produto pesqId(int valor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Produto where pro_id = :parametro");
		consulta.setInteger("parametro", valor);
		sessao.close();
		return (Produto) consulta.uniqueResult();
	}
	
	}

