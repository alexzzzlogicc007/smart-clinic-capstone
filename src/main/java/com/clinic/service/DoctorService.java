package com.clinic.service;

import com.clinic.model.Doctor;
import com.clinic.repo.AppointmentRepository;
import com.clinic.repo.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {
  private final DoctorRepository doctorRepo;
  private final AppointmentRepository apptRepo;

  public DoctorService(DoctorRepository d, AppointmentRepository a){this.doctorRepo=d; this.apptRepo=a;}

  public List<java.time.LocalTime> getAvailableSlots(Long doctorId, LocalDate date){
    Doctor d = doctorRepo.findById(doctorId).orElseThrow();
    Set<java.time.LocalTime> booked = apptRepo
      .findByDoctorIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(
          doctorId, date.atStartOfDay(), date.plusDays(1).atStartOfDay())
      .stream().map(ap -> ap.getAppointmentTime().toLocalTime()).collect(Collectors.toSet());
    return d.getAvailableTimes().stream().filter(t -> !booked.contains(t)).collect(Collectors.toList());
  }

  public boolean validateLogin(String email, String rawPassword){
    return doctorRepo.findByUserEmail(email)
        .map(d -> d.getUser().passwordMatches(rawPassword))
        .orElse(false);
  }
}
