package model;

import infrastructure.Builder;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Address object. Represents addresses without being an entity.
 * It is an embeddable object to fill in to entity properties.
 */
@Embeddable
public class Address {

    @NotNull
    @Max(message = "Name of street is too long. Maximal 100 letters.", value = 100)
    private String street;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    @Positive
    private long house_number;

    @NotNull
    @Digits(integer = 5, fraction = 0)
    @Positive
    private long zip_code;

    @NotNull
    @Max(value = 50)
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
