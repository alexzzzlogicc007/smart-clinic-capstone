package com.clinic.repo;

import com.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  Optional<Patient> findByEmail(String email);
  Optional<Patient> findByEmailOrPhone(String email, String phone);
}
