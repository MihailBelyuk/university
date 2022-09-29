package com.solvd.university.domain.university.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemindHolder {

    private static final Map<RemindType, List<IRemind>> HOLDER = new HashMap<>();

    public static void setReminder(IRemind student, RemindType remindType) {
        HOLDER.computeIfAbsent(remindType, k -> new ArrayList<>());
        HOLDER.get(remindType).add(student);
    }

    public static void cancelReminder(IRemind student, RemindType remindType) {
        if (HOLDER.get(remindType) != null) {
            List<IRemind> students = HOLDER.get(remindType);
            students.remove(students.stream()
                    .filter(st -> st.equals(student))
                    .findFirst().get());
            HOLDER.put(remindType, students);
        }
    }

    public static void notify(RemindType remindType) {
        List<IRemind> reminders = HOLDER.get(remindType);
        if (reminders != null) {
            reminders.forEach(reminder -> reminder.remind(remindType));
        }
    }
}
