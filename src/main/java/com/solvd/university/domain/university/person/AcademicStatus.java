package com.solvd.university.domain.university.person;

public enum AcademicStatus {

    JUNIOR_RESEARCHER("junior researcher"),
    ASSISTANT("assistant"),
    SENIOR_RESEARCHER("senior researcher"),
    DOCENT("docent"),
    PROFESSOR("professor");

    private final String displayName;

    AcademicStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

