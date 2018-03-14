package controller;

import infrastructure.CustomerRepository;
import model.Customer;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.ItemSelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.faces.webapp.FacesServlet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In many applications the controller layer, the service layer and the repository layer are not properly separated.
 * Ultimately it depends on the software developers thoroughness (Gründlichkeit) to do so. Finally to consider
 * the single response pattern we do this segregation.
 * Often you have at last one backing bean per view. But I is common to have more than one backing bean per view.
 * It is the responsibility to the software developer to look for the best optimum of class size.
 */
@Named
@ViewScoped
public class CustomerViewController implements Serializable{

    @Inject
    private CustomerRepository customerRepository;

    // property of my backing bean and the representation of table rows in view.
    private List<Customer> customerList = new ArrayList<>();

    private List<Customer> selectedCustomerList = new ArrayList<>();

    /**
     * After bean is instantiated by its constructor this method is called because of its annotation
     * to initialize a default starting state.
     */
    @PostConstruct
    public void init() {

        // load result set to table in view
        customerList = customerRepository.findAll();
        // after landing on page nothing will be selected
        selectedCustomerList = Collections.emptyList();
    }

    /**
     * Method to be called in general, if I want to display some kind of
     * information in a {@link FacesMessage} on my page. Unfortunately, message duration
     * has to be set in the corresponding *.xhtml page.
     *
     * @param msgSummery        Messages main part.
     * @param msgDetail         Messages sub part.
     */
    public void pushFacesMsgToGrowl(String msgSummery, String msgDetail) {

        // create desired message
        FacesMessage growlMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msgSummery, msgDetail);
        // add message to current faces context to immediately displaying it
        FacesContext.getCurrentInstance().addMessage(null, growlMsg);
    }

    /**
     * Method called by event listener in front end.
     *
     * @param event         The frontend event triggered by faces context handled over
     *                      to backing bean.
     */
    @Transactional
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        // Was really a change done? If yes, do frontend callback and merge persistence context
        if(newValue != null && !newValue.equals(oldValue)) {

            String msgSummery = "Entry changed:";
            String msgDetail = "Old: " + oldValue + ", New: " + newValue;
            pushFacesMsgToGrowl(msgSummery, msgDetail);

            customerRepository.mergeCustomer(customerList.get(event.getRowIndex()));

//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            try {
//                ec.redirect("CustomerView.xhtml");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Transactional
    public void rollCustomers100() {
        CustomerRepository.persistRandomCustomers(100);
    }

    @Transactional
    public void deleteCustomer() {
        System.out.println("Kunde wird gelöscht...");
        if (selectedCustomerList.size() == 1) {
            customerRepository.deleteCustomer(selectedCustomerList.get(0));
        } else {
            pushFacesMsgToGrowl(
                    "Bitte nur einen Kunden selektieren, der gelöscht werden soll.",
                    "Die Menge der selektierten Kunden ist zu groß. Es kann immer nur ein Kunde gelöscht werden"
            );
        }
    }

    @Transactional
    public void deleteAllCustomers() {
        customerRepository.deleteAllCustomers();
    }

    public String getSelectionSizeAsString() {
        return String.valueOf(selectedCustomerList.size());
    }

    public int getSelectionSizeAsInt() {
        return selectedCustomerList.size();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Customer> getSelectedCustomerList() {
        return selectedCustomerList;
    }

    public void setSelectedCustomerList(List<Customer> selectedCustomerList) {
        this.selectedCustomerList = selectedCustomerList;
    }
}
