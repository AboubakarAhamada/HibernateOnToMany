/**
 * 
 */
package com.example;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Aboubakar
 *
 */
public class StockManeger {

	public static void main(String[] args) {

		// Set configurations and mapps :
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

		// Create a session factory :
		
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//get the session :
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Create objects to insert into database:
		
		Category category= new Category("Ordinateur");
		
		Product pc = new Product("DELL PC"," Très performante pour les calculs distribués", 4000,category);
		Product laptop = new Product("HP", "Core i3 avec 4 Go de RAM", 1200,category);
		Product phone = new Product("iPhone 6", "Incassable et industructible", 5, category);
		Product tablette = new Product("Galaxy S5", "Bring it where you go", 250,category);
		
		// Now we gather all products:
		
		Set<Product> products = new HashSet<Product>();
		products.add(pc);
		products.add(laptop);
		products.add(phone);
		products.add(tablette);
		
		category.setProducts(products);
		
		// Save products in the database:
		
		session.save(category);
		
		session.getTransaction().commit();
		session.close();

	}

}
