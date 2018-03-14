package model;

import infrastructure.Builder;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Address object. Represents addresses without being an entity.
 * It is an embeddable object to fill in to entity properties.
 */
@Embeddable
public class Address {

    @NotNull
    private String street;

    @NotNull
    @Digits( integer = 3, fraction = 0 )
    @Positive
    private long house_number;

    @NotNull
    @Digits( integer = 5, fraction = 0 )
    @Positive
    private long zip_code;

    @NotNull
    private String location;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getHouse_number() {
        return house_number;
    }

    public void setHouse_number(long house_number) {
        this.house_number = house_number;
    }

    public long getZip_code() {
        return zip_code;
    }

    public void setZip_code(long zip_code) {
        this.zip_code = zip_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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
