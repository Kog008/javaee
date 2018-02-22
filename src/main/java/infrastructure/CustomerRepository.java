package infrastructure;

import model.Address;
import model.Customer;
import start.Forenames;
import start.Locations;
import start.Streets;
import start.Surnames;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

@RequestScoped
public class CustomerRepository {

    private EntityManager entityManager;

    public CustomerRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaee");
        entityManager = emf.createEntityManager();
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> findAll() {

        return entityManager
                .createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }

    /**
     * Method to load database with randomly created customer objects. To be callable from main method
     * it instantiates its own entity manager object and does not use the instance variable.
     *
     * @param quantity          Determines, how many customer objects are going to be inserted to the
     *                          given persistence context / database.
     */
    public static void persistRandomCustomers(int quantity) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaee");
        EntityManager em = emf.createEntityManager();

        Forenames[] forenames = Forenames.values();
        Surnames[] surnames = Surnames.values();
        Streets[] streets = Streets.values();
        Locations[] locations = Locations.values();

        for (int i = 0; i < quantity; i++) {
            Customer cus = Customer.createCustomerBuilder()
                    .withForename(forenames[(int) (Math.random()*forenames.length)].toString())
                    .withSurname(surnames[(int) (Math.random()*surnames.length)].toString())
                    .withAutomatedGeneratedEmail()
                    .withAddress(
                            Address
                                    .createAddress()
                                    .withStreet(streets[(int) (Math.random()*streets.length)].toString())
                                    .withHouseNumber((long) (Math.random()*1000))
                                    .withZipCode((long) (Math.random()*99999))
                                    .withLocation(locations[(int) (Math.random()*locations.length)].toString())
                                    .build()
                    )
                    .build();

            em.persist(cus);
        }

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
