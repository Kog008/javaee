package infrastructure;

import model.Address;
import model.Customer;
import start.Forenames;
import start.Locations;
import start.Streets;
import start.Surnames;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

/**
 * Repository classes typically implement the basic crud operations, like create, read, update and delete.
 * Usually they are injected in service layer classes to persist the processed entity objects.
 */
@RequestScoped
public class CustomerRepository {

    @Inject
    private EntityManager entityManager;

    public void deleteCustomer(Customer toRemove) {
        entityManager.remove(entityManager.find(Customer.class, toRemove.getId()));
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
    }

    public List<Customer> findAll() {

        return entityManager
                .createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }

    public void mergeCustomer(Customer editedCustomer) {
        entityManager.getTransaction().begin();
        entityManager.merge(editedCustomer);
        entityManager.getTransaction().commit();
    }

    public void deleteAllCustomers() {
        entityManager.getTransaction().begin();
        entityManager
                .createNamedQuery("Customer.deleteAll")
                .executeUpdate();
        entityManager.getTransaction().commit();
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

        System.out.println("Transaction begin");
        em.getTransaction().begin();

        for (int i = 0; i < quantity; i++) {
            Customer cus = Customer.createCustomerBuilder()
                    .withForename(forenames[(int) (Math.random()*forenames.length)].toString())
                    .withSurname(surnames[(int) (Math.random()*surnames.length)].toString())
                    .withAutomatelyGeneratedEmail()
                    .withAddress(
                            Address
                                    .createAddress()
                                    .withStreet(streets[(int) (Math.random()*streets.length)].toString())
                                    .withHouseNumber((long) (Math.random()*100))
                                    .withZipCode((long) (Math.random()*99999))
                                    .withLocation(locations[(int) (Math.random()*locations.length)].toString())
                                    .build()
                    )
                    .build();

            em.persist(cus);

            if ( i % 25 == 0 )
                em.flush();
        }

        System.out.println("Transaction commit");
        em.getTransaction().commit();

        while(em.getTransaction().isActive()) {
            System.out.println("Wait for transaction to end");
        }

        em.close();
        emf.close();
    }
}
