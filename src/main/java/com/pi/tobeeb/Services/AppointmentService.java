package com.pi.tobeeb.Services;

import com.pi.tobeeb.Entities.Appointment;
import com.pi.tobeeb.Entities.User;
import com.pi.tobeeb.Interfaces.AppointmentInterface;
import com.pi.tobeeb.Repositorys.AppointmentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService implements AppointmentInterface {

    AppointmentRepo appointmentRepo;

    @Override
    public List<Appointment> retrieveAllAppointment() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        LocalDate dateStart = appointment.getDateStart();
        List<Appointment> appointmentsOnDate = appointmentRepo.findBydateStart(dateStart);

        if (appointmentsOnDate.isEmpty()) {
            // si aucune réservation n'existe pour cette date, enregistrez simplement la nouvelle réservation
            return appointmentRepo.save(appointment);
        } else {
            // sinon, la date est déjà prise, renvoyer une erreur ou une exception
            throw new IllegalArgumentException("La date de rendez-vous est déjà prise");
        }
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        LocalDate currentDate = LocalDate.now();
        LocalDate reservationDate = appointment.getDateStart();

        if (reservationDate.isBefore(currentDate.plusDays(1))) {
            throw new IllegalArgumentException("Reservations can only be updated one day before the current date");
        }

        int appointmentId = appointment.getIdAppointment();
        Optional<Appointment> existingAppointment = appointmentRepo.findById(appointmentId);

        if (existingAppointment.isPresent()) {
            Appointment updatedAppointment = appointmentRepo.save(appointment);
            return updatedAppointment;
        } else {
            throw new IllegalArgumentException("Appointment with ID " + appointmentId + " not found");
        }
    }

    @Override
    public void removeAppointment(Appointment abonnement) {
        appointmentRepo.delete(abonnement);
    }

    @Override
    public Appointment retrieveAppointment(int idAppointment) {
        return appointmentRepo.findById(idAppointment).get();
    }

    public List<Appointment> findAppointmentsByUser(User user) {
        return appointmentRepo.findByPatient(user);
    }

    public List<Appointment> findAppointmentsByDoctor(User user) {
        return appointmentRepo.findByPatient(user);
    }
}
