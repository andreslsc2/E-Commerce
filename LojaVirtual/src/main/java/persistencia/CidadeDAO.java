package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import beans.Cidade;
import beans.End_Estado;


public class CidadeDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Cidade> buscaPorEstado(int estado_codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		/* Query consulta;
		consulta = sessao.createQuery("from end_cidade  "
					+ "where est_id like :parametro order by cid_nome ");
		 consulta.setString("parametro", "%" + estado_codigo + "%"); */
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("end_Estado.id", estado_codigo));
			consulta.addOrder(Order.asc("nome"));
			
			List<Cidade> resultado = consulta.list();
			
			return resultado;
		} catch (Exception e) {
			System.out.println("Erro ao tentar carregar Cidades ao abrir o formulário de pessoa.");
			e.printStackTrace();
			return null;
		}finally{
			sessao.close();
		}
	}
	
	public static List<Cidade> listagem() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
	
		consulta = sessao.createQuery("from Cidade order by cid_nome ");
	
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
		
}

