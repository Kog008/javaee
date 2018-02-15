package business.model;

import infrastructure.Builder;

import javax.persistence.Embeddable;

/**
 * Address object. Represents addresses without being an entity.
 * It is an embeddable object to fill in to entity properties.
 */
@Embeddable
public class Address {

    private String street;
    private long house_number;
    private long zip_code;
    private String location;

    @Override
    public String toString() {
        return String.format("%s %d\n%d %s", street, house_number, zip_code, location);
    }

    public static AddressBuilder createAddress() {
        return new AddressBuilder();
    }

    public static class AddressBuilder extends Builder<Address> {
        public AddressBuilder withStreet(String streetString) {
            getInstance().street = streetString;
            return this;
        }

        public AddressBuilder withHouseNumber(long houseNumber) {
            getInstance().house_number = houseNumber;
            return this;
        }

        public AddressBuilder withZipCode(long zip) {
            getInstance().zip_code = zip;
            return this;
        }

        public AddressBuilder withLocation(String location) {
            getInstance().location = location;
            return this;
        }
    }
}
