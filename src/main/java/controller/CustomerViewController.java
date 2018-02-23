package controller;

import infrastructure.CustomerRepository;
import model.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * In many applications the controller layer, the service layer and the repository layer are not properly separated.
 * Ultimately it depends on the software developers thoroughness (Gründlichkeit) to do so. Finally to consider
 * the single response pattern we do this segregation.
 * Often you have at last one backing bean per view. But I is common to have more than one backing bean per view.
 * It is the responsibility to the software developer to look for the best optimum of class size.
 */
@Named
@SessionScoped
public class CustomerViewController implements Serializable{

    @Inject
    private CustomerRepository customerRepository;

    // property of my backing bean and the representation of table rows in view.
    private List<Customer> customerList = new ArrayList<>();

    /**
     * Method is called by a listener in frontend to initialize my backing bean.
     */
    public void init() {

        customerList = customerRepository.findAll();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
