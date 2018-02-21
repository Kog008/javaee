package controller;

import infrastructure.CustomerService;
import model.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CustomerViewController {

    @Inject
    private CustomerService customerService;

    private List<Customer> customerList = new ArrayList<>();

    public void init() {
        customerList = customerService.findAll();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
