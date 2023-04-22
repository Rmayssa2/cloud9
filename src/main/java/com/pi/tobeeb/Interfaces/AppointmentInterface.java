package com.pi.tobeeb.Interfaces;

import com.pi.tobeeb.Entities.Appointment;

import java.util.List;

public interface AppointmentInterface {

    List<Appointment> retrieveAllAppointment();
    Appointment addAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);
    void removeAppointment(Appointment abonnement);
    Appointment retrieveAppointment (int idAppointment);


}
