package com.solvd.university.domain.university.person;

import com.solvd.university.domain.address.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dean {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Address address;
    private BigDecimal salary;

    public static DeanBuilder builder() {
        return new DeanBuilder(new Dean());
    }

    public DeanBuilder toBuilder() {
        return new DeanBuilder(this);
    }

    public static class DeanBuilder {

        private final Dean dean;

        public DeanBuilder(Dean dean) {
            this.dean = dean;
        }

        public DeanBuilder id(Long id) {
            this.dean.id = id;
            return this;
        }

        public DeanBuilder firstName(String firstName) {
            this.dean.firstName = firstName;
            return this;
        }

        public DeanBuilder lastName(String lastName) {
            this.dean.lastName = lastName;
            return this;
        }

        public DeanBuilder birthday(LocalDate birthday) {
            this.dean.birthday = birthday;
            return this;
        }

        public DeanBuilder salary(BigDecimal salary) {
            this.dean.salary = salary;
            return this;
        }

        public DeanBuilder address(Address address) {
            this.dean.address = address;
            return this;
        }

        public Dean build() {
            return dean;
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dean{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}
