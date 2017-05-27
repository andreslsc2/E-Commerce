package teste;

import org.hibernate.Session;

import persistencia.HibernateUtil;

public class HibernateTeste {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.close();
		HibernateUtil.getSessionFactory().close();
	}

}

