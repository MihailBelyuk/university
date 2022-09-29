package com.solvd.university.domain.university;

import com.solvd.university.domain.university.person.Teacher;

import java.util.List;

public class Chair {

    private Long id;
    private String name;
    private List<Teacher> teachers;

    public static ChairBuilder builder() {
        return new ChairBuilder(new Chair());
    }

    public ChairBuilder toBuilder() {
        return new ChairBuilder(this);
    }

    public static class ChairBuilder {
        private final Chair chair;

        public ChairBuilder(Chair chair) {
            this.chair = chair;
        }

        public ChairBuilder id(Long id) {
            this.chair.id = id;
            return this;
        }

        public ChairBuilder name(String name) {
            this.chair.name = name;
            return this;
        }

        public ChairBuilder teachers(List<Teacher> teachers) {
            this.chair.teachers = teachers;
            return this;
        }

        public Chair build() {
            return chair;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
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
