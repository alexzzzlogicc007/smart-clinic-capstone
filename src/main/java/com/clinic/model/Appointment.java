package com.clinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false) @JoinColumn(name = "doctor_id", nullable = false)
  private Doctor doctor;

  @ManyToOne(optional = false) @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;

  @NotNull @FutureOrPresent
  @Column(name="appointment_time", nullable = false)
  private LocalDateTime appointmentTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.BOOKED;

  public enum Status { BOOKED, CANCELLED, DONE }

  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public Doctor getDoctor(){return doctor;}
  public void setDoctor(Doctor doctor){this.doctor=doctor;}
  public Patient getPatient(){return patient;}
  public void setPatient(Patient patient){this.patient=patient;}
  public LocalDateTime getAppointmentTime(){return appointmentTime;}
  public void setAppointmentTime(LocalDateTime t){this.appointmentTime=t;}
  public Status getStatus(){return status;}
  public void setStatus(Status status){this.status=status;}
}
