package com.solvd.university.university;

import com.solvd.university.university.person.Dean;
import com.solvd.university.university.person.Student;

import java.util.List;

public class Faculty {

    private long id;
    private String name;
    private Dean dean;
    private List<Chair> chairs;
    private List<Student> students;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public List<Chair> getDepartments() {
        return chairs;
    }

    public void setDepartments(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
