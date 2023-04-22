package com.pi.tobeeb.Repositorys;

import com.pi.tobeeb.Entities.Appointment;
import com.pi.tobeeb.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findBydateStart(LocalDate dateStart);
    List<Appointment> findByPatient(User user);


    List<Appointment> findByDoctor(User user);

}
