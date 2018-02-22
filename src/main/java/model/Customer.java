package model;

import infrastructure.Builder;

// Annotations to hibernate persistence / JPA 2.1
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// Annotations to hibernate validation / Bean Validation 2.0
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

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
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")


// Following hibernate annotations. to mark this class as part of the persistence context.
// This means that this class is going to be mapped to your database as a table.
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private long id;

    @NotNull
    @Email
    @Size(max = 150)
    @Column(name = "c_email")
    private String email;

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
    @Null
    private Address address;

    @NotNull
    @FutureOrPresent
    @Column(name = "c_registrationDate")
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s %s\n%s", email, forename, surname, address);
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

        public CustomerBuilder withEmail(String emailString) {
            getInstance().email = emailString;
            return this;
        }

        public CustomerBuilder withAutomatedGeneratedEmail() {
            getInstance().email = getInstance().forename
                            + "." + getInstance().surname
                            + "@" + getInstance().id + ".com";
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
            getInstance().registrationDate = LocalDateTime.now();
            return super.build();
        }
    }
}
