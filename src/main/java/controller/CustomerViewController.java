package controller;

import infrastructure.CustomerRepository;
import model.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CustomerViewController implements Serializable{

    @Inject
    private CustomerRepository customerRepository;

    private List<Customer> customerList = new ArrayList<>();

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
