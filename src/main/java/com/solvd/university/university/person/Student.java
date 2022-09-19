package com.solvd.university.university.person;

import com.solvd.university.address.Address;
import com.solvd.university.document.StudentCard;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private StudentCard studentCard;
    private Address address;
    private BigDecimal scholarship;
    private Integer course;
    private boolean hasScholarship;
    private boolean livesInHostel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

    public BigDecimal getScholarship() {
        return scholarship;
    }

    public void setScholarship(BigDecimal scholarship) {
        this.scholarship = scholarship;
    }

    public boolean isLivesInHostel() {
        return livesInHostel;
    }

    public void setLivesInHostel(boolean livesInHostel) {
        this.livesInHostel = livesInHostel;
    }

    public boolean isHasScholarship() {
        return hasScholarship;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }
}
