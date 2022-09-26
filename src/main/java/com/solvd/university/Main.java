package com.solvd.university;

import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.university.Chair;
import com.solvd.university.domain.university.Faculty;
import com.solvd.university.domain.university.person.AcademicStatus;
import com.solvd.university.domain.university.person.Dean;
import com.solvd.university.domain.university.person.Teacher;
import com.solvd.university.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Address address = new Address();
        address.setCity("Orsha");
        address.setStreet("Dovatora str.");
        address.setHouse(29);
        address.setIndex(123456);
        address.setId(80L);
        AddressServiceImpl addressService = new AddressServiceImpl();
        addressService.create(address);
        System.out.println(addressService.getById(2L));

        Teacher teacher = new Teacher();
        teacher.setId(29L);
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        teacherService.delete(teacher);

        Chair chair = new Chair();
        chair.setId(4L);
        chair.setName("Chemistry");
        ChairServiceImpl chairService = new ChairServiceImpl();
        chairService.update(chair);

        FacultyServiceImpl facultyService = new FacultyServiceImpl();
        facultyService.getAll().forEach(System.out::println);
        System.out.println(facultyService.getById(1L));

        System.out.println(address);
        address.setCity("Minsk");
        address.setStreet("Lenina");
        address.setHouse(7);
        address.setFlat(1);
        address.setIndex(2236655);
        addressService.update(address);
        System.out.println(addressService.getAll());

        Chair chair1 = new Chair();
        chair.setName("Economics");
        chairService.create(chair1);
        System.out.println(chairService.getAll());

        DeanServiceImpl deanService = new DeanServiceImpl();
        Dean dean = new Dean();
        dean.setFirstName("Vadim");
        dean.setLastName("Vadimov");
        dean.setBirthday(LocalDate.of(1980, 3, 3));
        dean.setSalary(new BigDecimal(700));
        deanService.create(dean, 13L);

        Dean dean1 = deanService.getById(6L);
        dean.setFirstName("Leon");
        deanService.update(dean1, 13L);
        deanService.delete(dean);

        System.out.println(deanService.getAll());
        Faculty faculty = new Faculty();
        faculty.setId(6L);
        Faculty faculty1 = facultyService.getById(7L);
        faculty.setName("Veterinary medicine");
        facultyService.update(faculty, 1L, 3L);
        System.out.println(facultyService.getAll());
        System.out.println(facultyService.getById(7L));

        Teacher teacher1 = new Teacher();
        teacher.setFirstName("Marry");
        teacher.setLastName("Poppins");
        teacher.setBirthday(LocalDate.of(1970, 4, 4));
        teacher.setSalary(new BigDecimal(5000));
        teacher.setAcademicStatus(AcademicStatus.PROFESSOR);
        teacherService.create(teacher1,18L,5L);
    }
}
