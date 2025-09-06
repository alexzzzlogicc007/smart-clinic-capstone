package com.clinic.api;

import com.clinic.service.DoctorService;
import com.clinic.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
  private final DoctorService doctorService;
  private final TokenService tokenService;

  public DoctorController(DoctorService doctorService, TokenService tokenService){
    this.doctorService=doctorService; this.tokenService=tokenService;
  }

  @GetMapping("/{id}/availability")
  public ResponseEntity<?> getAvailability(
      @RequestHeader(value="Authorization",required=false) String auth,
      @PathVariable Long id,
      @RequestParam LocalDate date){
    tokenService.validateBearer(auth);
    return ResponseEntity.ok(doctorService.getAvailableSlots(id, date));
  }
}
