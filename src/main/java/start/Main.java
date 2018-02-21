package start;

import model.Address;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaee");
        EntityManager em = emf.createEntityManager();

        Customer cus = Customer.createCustomerBuilder()
                .withEmail("mrmiagi@karate.hk")
                .withForename("Karate")
                .withSurname("Kit")
                .withAddress(
                        Address
                                .createAddress()
                                .withStreet("Elmstreet")
                                .withHouseNumber(1246)
                                .withZipCode(39365)
                                .withLocation("New York")
                                .build()
                )
                .build();

        System.out.println(cus);


        em.getTransaction().begin();
        em.persist(cus);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
