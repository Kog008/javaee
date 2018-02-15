package start;

import business.model.Address;
import business.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaee");
        EntityManager em = emf.createEntityManager();

        Customer cus = Customer.createCustomerBuilder()
                .withEmail("cus@welt.com")
                .withForename("John")
                .withSurname("Doe")
                .withAddress(
                        Address
                                .createAddress()
                                .withStreet("Allerweg")
                                .withHouseNumber(8)
                                .withZipCode(39365)
                                .withLocation("Nowhere")
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
