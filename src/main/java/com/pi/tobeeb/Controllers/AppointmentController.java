package com.pi.tobeeb.Controllers;

import com.pi.tobeeb.Entities.Appointment;
import com.pi.tobeeb.Entities.User;
import com.pi.tobeeb.Repositorys.AppointmentRepo;
import com.pi.tobeeb.Services.AppointmentService;
import com.pi.tobeeb.Services.EmailService;
import com.pi.tobeeb.Services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired

    EmailService emailService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PaymentService paymentService;

     AppointmentRepo appointmentRepository;
    @GetMapping("/appointment")

    public List<Appointment> getCours()
    {
        return appointmentService.retrieveAllAppointment();

    }
    @GetMapping("/send-email")
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("rafed.benjeddou@esprit.tn");
            message.setSubject("Test e-mail");
            message.setText("This is a test e-mail from my Spring Boot application.");
            javaMailSender.send(message);
            return "E-mail sent successfully.";
        } catch (Exception ex) {
            return "Error sending e-mail: " + ex.getMessage();
        }
    }


    @PostMapping("/addappointment")
    public ResponseEntity<String> addAppointment(@Validated @RequestBody Appointment appointment) {
        try {
            appointmentService.addAppointment(appointment);

            return ResponseEntity.ok("Reservation added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Reservation already taken");
        }
    }

    @PutMapping("/updateappointment/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable int id, @Validated @RequestBody Appointment appointment) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate reservationDate = appointment.getDateStart();

            if (reservationDate.isBefore(currentDate.plusDays(1))) {
                throw new IllegalArgumentException("Reservations can only be updated one day before the current date");
            }

            Appointment existingAppointment = appointmentService.retrieveAppointment(id);
            if (existingAppointment == null) {
                throw new IllegalArgumentException("Appointment with id " + id + " not found");
            }

            if (!existingAppointment.getDateStart().equals(appointment.getDateStart())) {
                throw new IllegalArgumentException("Reservations can only be updated one day before the current date");
            }

            appointment.setIdAppointment(id);
            appointmentService.updateAppointment(appointment);
            return ResponseEntity.ok("Reservation updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteappointment")

    public String deletePiste(@Validated @RequestBody Appointment appointment)
    {
        appointmentService.removeAppointment(appointment);
        return "Deleted Successfully";
    }
    @GetMapping("/appointment{id}")

    public Appointment AffichercourstById(@PathVariable("id") int appointment)
    {
        return appointmentService.retrieveAppointment(appointment);
    }

    @GetMapping("/patient/{userId}/appointments")
    public List<Appointment> getAppointmentsByUser(@PathVariable int userId) {
        User user = new User();
        user.setIdUser(userId);
        return appointmentService.findAppointmentsByUser(user);
    }

    @GetMapping("/doctor/{userId}/appointments")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable int userId) {
        User user = new User();
        user.setIdUser(userId);
        return appointmentService.findAppointmentsByDoctor(user);
    }
    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestParam("amount") Long amount,
                                    @RequestParam("currency") String currency,
                                    @RequestParam("description") String description,
                                    @RequestParam("source") String source) {
        try {
            Charge charge = paymentService.createCharge(amount, currency, description, source);
            return ResponseEntity.ok("charge");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
