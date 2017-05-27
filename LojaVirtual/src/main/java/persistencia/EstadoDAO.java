package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.End_Estado;

public class EstadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<End_Estado> listagem() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
	
		consulta = sessao.createQuery("from End_Estado order by est_nome ");
	
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
}
