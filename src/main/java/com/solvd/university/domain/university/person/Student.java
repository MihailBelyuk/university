package com.solvd.university.domain.university.person;

import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.document.StudentCard;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Student implements IRemind {

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

    public static StudentBuilder builder() {
        return new StudentBuilder(new Student());
    }

    public StudentBuilder toBuilder() {
        return new StudentBuilder(this);
    }

    @Override
    public void remind(RemindType remindType) {
        System.out.println(this + "is reminded of coming " + remindType);
    }

    public static class StudentBuilder {

        private final Student student;

        public StudentBuilder(Student student) {
            this.student = student;
        }

        public StudentBuilder id(Long id) {
            this.student.id = id;
            return this;
        }

        public StudentBuilder firstName(String firstName) {
            this.student.firstName = firstName;
            return this;
        }

        public StudentBuilder lastName(String lastName) {
            this.student.lastName = lastName;
            return this;
        }

        public StudentBuilder birthday(LocalDate birthday) {
            this.student.birthday = birthday;
            return this;
        }

        public StudentBuilder studentCard(StudentCard studentCard) {
            this.student.studentCard = studentCard;
            return this;
        }

        public StudentBuilder address(Address address) {
            this.student.address = address;
            return this;
        }

        public StudentBuilder scholarship(BigDecimal scholarship) {
            this.student.scholarship = scholarship;
            return this;
        }

        public StudentBuilder course(Integer course) {
            this.student.course = course;
            return this;
        }

        public StudentBuilder hasScholarship(boolean hasScholarship) {
            this.student.hasScholarship = hasScholarship;
            return this;
        }

        public StudentBuilder livesInHostel(boolean livesInHostel) {
            this.student.livesInHostel = livesInHostel;
            return this;
        }

        public Student build() {
            return student;
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

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public Address getAddress() {
        return address;
    }

    public BigDecimal getScholarship() {
        return scholarship;
    }

    public Integer getCourse() {
        return course;
    }

    public boolean isHasScholarship() {
        return hasScholarship;
    }

    public boolean isLivesInHostel() {
        return livesInHostel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (hasScholarship != student.hasScholarship) return false;
        if (livesInHostel != student.livesInHostel) return false;
        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (birthday != null ? !birthday.equals(student.birthday) : student.birthday != null) return false;
        if (studentCard != null ? !studentCard.equals(student.studentCard) : student.studentCard != null) return false;
        if (address != null ? !address.equals(student.address) : student.address != null) return false;
        if (scholarship != null ? !scholarship.equals(student.scholarship) : student.scholarship != null) return false;
        return course != null ? course.equals(student.course) : student.course == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (studentCard != null ? studentCard.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (scholarship != null ? scholarship.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (hasScholarship ? 1 : 0);
        result = 31 * result + (livesInHostel ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", studentCard=").append(studentCard);
        sb.append(", address=").append(address);
        sb.append(", scholarship=").append(scholarship);
        sb.append(", course=").append(course);
        sb.append(", hasScholarship=").append(hasScholarship);
        sb.append(", livesInHostel=").append(livesInHostel);
        sb.append('}');
        return sb.toString();
    }
}
