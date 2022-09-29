package com.solvd.university.domain.university.person;

import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.university.IWrite;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rector {

    private IWrite write;
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Address address;
    private BigDecimal salary;

    public static RectorBuilder builder() {
        return new RectorBuilder(new Rector());
    }

    public RectorBuilder toBuilder() {
        return new RectorBuilder(this);
    }

    public static class RectorBuilder {

        private final Rector rector;

        public RectorBuilder(Rector rector) {
            this.rector = rector;
        }

        public RectorBuilder id(Long id) {
            this.rector.id = id;
            return this;
        }

        public RectorBuilder firstName(String firstName) {
            this.rector.firstName = firstName;
            return this;
        }

        public RectorBuilder lastName(String lastName) {
            this.rector.lastName = lastName;
            return this;
        }

        public RectorBuilder birthday(LocalDate birthday) {
            this.rector.birthday = birthday;
            return this;
        }

        public RectorBuilder address(Address address) {
            this.rector.address = address;
            return this;
        }

        public RectorBuilder salary(BigDecimal salary) {
            this.rector.salary = salary;
            return this;
        }

        public Rector build() {
            return rector;
        }
    }

    public IWrite getWrite() {
        return write;
    }

    public void setWrite(IWrite write) {
        this.write = write;
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
}
