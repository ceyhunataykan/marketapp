package com.ceyhunataykan.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        // Session durumu eğer null'sa işlem yapılacak
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate konfigurasyon dosyası ekleniyor.
                configuration.configure("hibernate.cfg.xml");
                // Service Registry hazırda bekletme servisidir. Hibernate 4.x itibaren kullanılıyor.
                ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                // Session Factory oluştuluyor.
                sessionFactory = configuration.buildSessionFactory(serviceRegistryObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
