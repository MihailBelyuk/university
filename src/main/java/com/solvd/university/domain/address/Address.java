package com.solvd.university.domain.address;

public class Address {

    private Long id;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
    private Integer index;

    public static AddressBuilder builder() {
        return new AddressBuilder(new Address());
    }

    public AddressBuilder toBuilder() {
        return new AddressBuilder(this);
    }

    public static class AddressBuilder {

        private final Address address;

        public AddressBuilder(Address address) {
            this.address = address;
        }

        public AddressBuilder id(Long id) {
            this.address.id = id;
            return this;
        }

        public AddressBuilder city(String city) {
            this.address.city = city;
            return this;
        }

        public AddressBuilder street(String street) {
            this.address.street = street;
            return this;
        }

        public AddressBuilder house(Integer house) {
            this.address.house = house;
            return this;
        }

        public AddressBuilder flat(Integer flat) {
            this.address.flat = flat;
            return this;
        }

        public AddressBuilder index(Integer index) {
            this.address.index = index;
            return this;
        }

        public Address build() {
            return address;
        }
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouse() {
        return house;
    }

    public Integer getFlat() {
        return flat;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house=").append(house);
        sb.append(", flat=").append(flat);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}
