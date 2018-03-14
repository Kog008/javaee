package model;

import infrastructure.Builder;

// Annotations to hibernate persistence / JPA 2.1
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// Annotations to hibernate validation / Bean Validation 2.0
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representation of the customer entity. To get a customer instance use the {@link CustomerBuilder}.
 *
 * @author kog008
 * @see infrastructure.CustomerRepository
 * @see CustomerBuilder
 * @see Builder
 */


/*
        There are a lot of different ways to define sql statements in jpa. A common way is to use
        hibernate sql, known as hql. To do so, best practice is to use annotation @NamedQuery.
        This annoation I have to handle two parameters: an identifier for this query - the name -
        and the query itself. The @NamedQuery has to be used in the targeted entity. But the query
        can be used everywhere else. In this example in the service class infrastructure.CustomerService.
 */
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.deleteAll", query = "DELETE FROM Customer")
})


// Following hibernate annotations. to mark this class as part of the persistence context.
// This means that this class is going to be mapped to your database as a table.
@Entity
@Table(name = "t_customer")
public class Customer {

    private static final String EMAIL_REGEX_KOMPLEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_REGEX_SIMPLE = "^[_a-z0-9-]+(.[a-z0-9-]+)@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private long id;

    @NotNull
    @Size(max = 50, message = "Email too long. Maximal 50 characters.")
    @Pattern(regexp = EMAIL_REGEX_SIMPLE)
    @Column(name = "c_email")
    private String customerEmail;

    @NotNull
    @Size(max = 50)
    @Column(name = "c_forename")
    private String forename;

    @NotNull
    @Size(max = 50)
    @Column(name = "c_surname")
    private String surname;

    /**
     * Complex types with own classes. Typically to provide reusability and clean code.
     * In this example it is very common, that many different entity objects will need an address.
     */
    @Embedded
    private Address address;

    @NotNull
    @Column(name = "c_registrationDate")
    private LocalDate registrationDate;

    @NotNull
    private LocalDateTime lastChange;

    public Long getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String newEmail) {
        customerEmail = newEmail;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s %s\n%s", customerEmail, forename, surname, address);
    }

    public static CustomerBuilder createCustomerBuilder() {
        return new CustomerBuilder();
    }

    /**
     * Inner class representing the concrete builder to this entity.
     * The static method <code>createCustomer()</code> deals a builder to calling
     * the inherited methods for manipulating the desired properties and finally
     * returns the type itself via calling <code>build()</code>.
     */
    public static class CustomerBuilder extends Builder<Customer> {

        @Override
        protected void validate() {

        }

        public CustomerBuilder withEmail(
                @Pattern(regexp = EMAIL_REGEX_SIMPLE, message = "Email does not fit the regex pattern.") String emailString) {

            getInstance().customerEmail = emailString;
            return this;
        }

        public CustomerBuilder withAutomatelyGeneratedEmail() {

            getInstance().customerEmail =
                    getInstance().forename.toLowerCase() + "@" +
                    getInstance().surname.toLowerCase() + ".com";

            return this;
        }

        public CustomerBuilder withForename(String forenameString) {
            getInstance().forename = forenameString;
            return this;
        }

        public CustomerBuilder withSurname(String surenameString) {
            getInstance().surname = surenameString;
            return this;
        }

        public CustomerBuilder withAddress(Address addressString) {
            getInstance().address = addressString;
            return this;
        }

        @Override
        public Customer build() {
            getInstance().registrationDate = LocalDate.now();
            getInstance().lastChange = LocalDateTime.now();
            return super.build();
        }
    }
}
