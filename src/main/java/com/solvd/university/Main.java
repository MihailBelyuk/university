package com.solvd.university;

import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.university.Chair;
import com.solvd.university.domain.university.person.Teacher;
import com.solvd.university.service.impl.AddressServiceImpl;
import com.solvd.university.service.impl.ChairServiceImpl;
import com.solvd.university.service.impl.FacultyServiceImpl;
import com.solvd.university.service.impl.TeacherServiceImpl;

public class Main {
    public static void main(String[] args) {
//        Address address = new Address();
//        address.setCity("Orsha");
//        address.setStreet("Dovatora str.");
//        address.setHouse(29);
//        address.setIndex(123456);
//        address.setId(80L);
//        AddressServiceImpl addressService = new AddressServiceImpl();
//        addressService.addAddress(address);
//
//        Teacher teacher = new Teacher();
//        teacher.setId(29L);
//        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        teacherService.deleteTeacher(teacher);
//
//        Chair chair = new Chair();
//        chair.setId(4L);
//        chair.setName("Chemistry");
//        ChairServiceImpl chairService = new ChairServiceImpl();
//        chairService.updateChair(chair);
//
        FacultyServiceImpl facultyService = new FacultyServiceImpl();
//        facultyService.showAllFaculties().forEach(System.out::println);
        System.out.println(facultyService.findFacultyById(7L));
    }
}
