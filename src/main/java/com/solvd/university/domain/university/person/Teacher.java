package com.solvd.university.domain.university.person;

import com.solvd.university.domain.address.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Teacher {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Address address;
    private BigDecimal salary;
    private AcademicStatus academicStatus;

    public static TeacherBuilder builder() {
        return new TeacherBuilder(new Teacher());
    }

    public TeacherBuilder toBuilder() {
        return new TeacherBuilder(this);
    }

    public static class TeacherBuilder {

        private final Teacher teacher;

        public TeacherBuilder(Teacher teacher) {
            this.teacher = teacher;
        }

        public TeacherBuilder id(Long id) {
            this.teacher.id = id;
            return this;
        }

        public TeacherBuilder firstName(String firstName) {
            this.teacher.firstName = firstName;
            return this;
        }

        public TeacherBuilder lastName(String lastName) {
            this.teacher.lastName = lastName;
            return this;
        }

        public TeacherBuilder birthday(LocalDate birthday) {
            this.teacher.birthday = birthday;
            return this;
        }

        public TeacherBuilder address(Address address) {
            this.teacher.address = address;
            return this;
        }

        public TeacherBuilder salary(BigDecimal salary) {
            this.teacher.salary = salary;
            return this;
        }

        public TeacherBuilder academicStatus(AcademicStatus academicStatus) {
            this.teacher.academicStatus = academicStatus;
            return this;
        }

        public Teacher build() {
            return teacher;
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

    public AcademicStatus getAcademicStatus() {
        return academicStatus;
    }
}
