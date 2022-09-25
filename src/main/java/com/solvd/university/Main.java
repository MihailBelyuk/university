package com.solvd.university;

import com.solvd.university.service.mybatisimpl.TeacherServiceImpl;

public class Main {
    public static void main(String[] args) {
//        Address address = new Address();
//        address.setCity("Orsha");
//        address.setStreet("Dovatora str.");
//        address.setHouse(29);
//        address.setIndex(123456);
//        address.setId(80L);
//        AddressServiceImpl addressService = new AddressServiceImpl();
//        addressService.create(address);
//
//        Teacher teacher = new Teacher();
//        teacher.setId(29L);
//        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        teacherService.delete(teacher);

//        Chair chair = new Chair();
//        chair.setId(4L);
//        chair.setName("Chemistry");
//        ChairServiceImpl chairService = new ChairServiceImpl();
//        chairService.update(chair);
//
//        FacultyServiceImpl facultyService = new FacultyServiceImpl();
//        facultyService.getAll().forEach(System.out::println);
//        System.out.println(facultyService.getById(1L));
//        AddressDaoImpl addressDao = new AddressDaoImpl();
//        Address address = addressDao.findById(2L).get();
//        System.out.println(address);
//        address.setCity("Minsk");
//        address.setStreet("Lenina");
//        address.setHouse(7);
//        address.setFlat(1);
//        address.setIndex(2236655);
//        addressDao.update(address);
//        System.out.println( addressDao.findAll());
//        ChairDaoImpl chairDao = new ChairDaoImpl();
//         Chair chair = new Chair();
//         chair.setName("Economics");
//         chairDao.create(chair);
//        System.out.println(chairDao.findAll());
//        DeanDaoImpl deanDao = new DeanDaoImpl();
//        Dean dean = new Dean();
//        dean.setFirstName("Vadim");
//        dean.setLastName("Vadimov");
//        dean.setBirthday(LocalDate.of(1980,3,3));
//        dean.setSalary(new BigDecimal(700));
//        deanDao.create(dean,13L);
//        Dean dean = deanDao.findById(6L).get();
//        dean.setFirstName("Leon");
//        deanDao.update(dean, 13L);
//        deanDao.delete(dean);
//        System.out.println(deanDao.findAll());
//        FacultyDaoImpl facultyDao = new FacultyDaoImpl();
//        Faculty faculty = new Faculty();
//        faculty.setId(6L);
//        Faculty faculty = facultyDao.findById(7L).get();
//        faculty.setName("Veterinary medicine");
//        facultyDao.update(faculty, 1L, 3L);
//        System.out.println(facultyDao.findAll());

//        Teacher teacher = new Teacher();
//        teacher.setFirstName("Marry");
//        teacher.setLastName("Poppins");
//        teacher.setBirthday(LocalDate.of(1970, 4, 4));
//        teacher.setSalary(new BigDecimal(5000));
//        teacher.setAcademicStatus(AcademicStatus.PROFESSOR);

        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        System.out.println(teacherService.getById(45L));
        System.out.println(teacherService.getAll());
    }

}
