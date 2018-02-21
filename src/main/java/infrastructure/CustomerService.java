package infrastructure;

import model.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@RequestScoped
public class CustomerService {

    @PersistenceContext( unitName = "javaee")
    private EntityManager entityManager;

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> findAll() {

        return entityManager
                .createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }


}
