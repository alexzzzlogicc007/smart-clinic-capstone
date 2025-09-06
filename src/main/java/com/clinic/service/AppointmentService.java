package com.clinic.service;

import com.clinic.model.Appointment;
import com.clinic.model.Doctor;
import com.clinic.model.Patient;
import com.clinic.repo.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
  private final AppointmentRepository repo;
  public AppointmentService(AppointmentRepository repo){this.repo=repo;}

  @Transactional
  public Appointment book(Long doctorId, Long patientId, LocalDateTime when){
    Appointment a = new Appointment();
    Doctor d = new Doctor(); d.setId(doctorId);
    Patient p = new Patient(); p.setId(patientId);
    a.setDoctor(d); a.setPatient(p); a.setAppointmentTime(when);
    return repo.save(a);
  }

  public List<Appointment> getForDoctorOnDate(Long doctorId, LocalDate date){
    LocalDateTime from = date.atStartOfDay();
    LocalDateTime to = date.plusDays(1).atStartOfDay();
    return repo.findByDoctorIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(doctorId, from, to);
  }
}
