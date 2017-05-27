package persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private static SessionFactory buildSessionFactory(){
		try{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			StandardServiceRegistryBuilder registro = new StandardServiceRegistryBuilder();
			
			registro.applySettings(cfg.getProperties());
			StandardServiceRegistry servico = registro.build();
						
			return cfg.buildSessionFactory(servico);
		}catch(Throwable ex){
			System.out.println("Problemas ao criar seção: ERRO:" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	

}