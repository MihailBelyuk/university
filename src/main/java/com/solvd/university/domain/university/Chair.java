package com.solvd.university.domain.university;

import com.solvd.university.domain.university.person.Teacher;

import java.util.List;

public class Chair {

    private Long id;
    private String name;
    private List<Teacher> teachers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chair{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", teachers=").append(teachers);
        sb.append('}');
        return sb.toString();
    }
}
