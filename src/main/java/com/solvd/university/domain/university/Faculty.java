package com.solvd.university.domain.university;

import com.solvd.university.domain.university.person.Dean;
import com.solvd.university.domain.university.person.Student;

import java.util.List;

public class Faculty {

    private Long id;
    private String name;
    private Dean dean;
    private List<Chair> chairs;
    private List<Student> students;

    public static FacultyBuilder builder() {
        return new FacultyBuilder(new Faculty());
    }

    public FacultyBuilder toBuilder() {
        return new FacultyBuilder(this);
    }

    public static class FacultyBuilder {
        private final Faculty faculty;


        public FacultyBuilder(Faculty faculty) {
            this.faculty = faculty;
        }

        public FacultyBuilder id(Long id) {
            this.faculty.id = id;
            return this;
        }

        public FacultyBuilder name(String name) {
            this.faculty.name = name;
            return this;
        }

        public FacultyBuilder dean(Dean dean) {
            this.faculty.dean = dean;
            return this;
        }

        public FacultyBuilder chairs(List<Chair> chairs) {
            this.faculty.chairs = chairs;
            return this;
        }

        public FacultyBuilder students(List<Student> students) {
            this.faculty.students = students;
            return this;
        }

        public Faculty build() {
            return faculty;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Dean getDean() {
        return dean;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dean=").append(dean);
        sb.append(", chairs=").append(chairs);
        sb.append(", students=").append(students);
        sb.append('}');
        return sb.toString();
    }
}
