package com.solvd.university;

import com.solvd.university.domain.computer.*;
import com.solvd.university.domain.university.AutoPark;
import com.solvd.university.domain.university.Pen;
import com.solvd.university.domain.university.Pencil;
import com.solvd.university.domain.university.person.Rector;
import com.solvd.university.domain.university.person.RemindHolder;
import com.solvd.university.domain.university.person.RemindType;
import com.solvd.university.domain.university.person.Student;
import com.solvd.university.domain.vehicle.Vehicle;
import com.solvd.university.domain.vehicle.VehicleFactory;
import com.solvd.university.domain.vehicle.VehicleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        /**
         * Pattern strategy. Is used to give rector opportunity to use
         * different writing appliances.  + builder
         */
        Pen pen = new Pen();
        pen.setBrand("Erich Krause");
        Pencil pencil = new Pencil();
        pencil.setBrand("Faber-Castell");

        Rector rector = new Rector();
        rector.toBuilder()
                .firstName("Anton")
                .lastName("Yatusevich")
                .build();
        rector.setWrite(pen);
        rector.getWrite().write();
        rector.getWrite().write();
        rector.getWrite().fixMarks();
        rector.setWrite(pencil);
        rector.getWrite().write();
        rector.getWrite().fixMarks();

        /**
         * Listener pattern is used to remind student of upcoming event. + builder
         */
        Student dasha = new Student();
        dasha.toBuilder()
                .firstName("Dasha")
                .build();
        Student masha = new Student();
        masha.toBuilder()
                .firstName("Masha")
                .build();
        Student sasha = new Student();
        sasha.toBuilder()
                .firstName("Sasha")
                .build();

        RemindHolder.setReminder(dasha, RemindType.HALLOWEEN);
        RemindHolder.setReminder(dasha, RemindType.SESSION);
        RemindHolder.setReminder(masha, RemindType.HALLOWEEN);
        RemindHolder.setReminder(sasha, RemindType.SESSION);
        RemindHolder.notify(RemindType.HALLOWEEN);
        RemindHolder.notify(RemindType.SESSION);
        RemindHolder.notify(RemindType.STUDENTS_DAY);
        LOGGER.info("");
        RemindHolder.cancelReminder(dasha, RemindType.SESSION);
        RemindHolder.notify(RemindType.HALLOWEEN);
        RemindHolder.notify(RemindType.SESSION);

        /**
         * Factory pattern is used to create vehicles to fill auto park.
         */
        Vehicle car = VehicleFactory.getVehicle(VehicleType.CAR);
        car.setBrand("Audi");
        Vehicle car2 = VehicleFactory.getVehicle(VehicleType.CAR);
        car2.setBrand("VW");
        Vehicle car3 = VehicleFactory.getVehicle(VehicleType.CAR);
        car3.setBrand("BMW");
        Vehicle van = VehicleFactory.getVehicle(VehicleType.VAN);
        van.setBrand("Mercedes");
        Vehicle bus = VehicleFactory.getVehicle(VehicleType.BUS);
        bus.setBrand("MAZ");
        AutoPark autoPark = new AutoPark();
        autoPark.setVehicleList(new ArrayList<>());
        autoPark.getVehicleList().add(car);
        autoPark.getVehicleList().add(car2);
        autoPark.getVehicleList().add(car3);
        autoPark.getVehicleList().add(van);
        autoPark.getVehicleList().add(bus);

        /**
         * Decorator pattern is used to wrap hardware information.
         */
        IComputer computer = new SimpleComputer();
        IComputer mouse = new Mouse(computer, new BigDecimal(5));
        IComputer keyboard = new Keyboard(mouse, new BigDecimal(10));
        IComputer videoCard = new VideoCard(keyboard, new BigDecimal(500));
        System.out.println(videoCard.getPrice());

        IComputer computer1 = new Monitor(new Mouse(new Keyboard(new VideoCard(new Processor(new SimpleComputer())))));
        System.out.println(computer1.getHardwareInformation());
    }
}
