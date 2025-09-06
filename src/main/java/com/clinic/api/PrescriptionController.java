package com.clinic.api;

import com.clinic.dto.PrescriptionRequest;
import com.clinic.model.Prescription;
import com.clinic.service.PrescriptionService;
import com.clinic.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
  private final TokenService tokenService;
  private final PrescriptionService service;

  public PrescriptionController(TokenService t, PrescriptionService s){this.tokenService=t; this.service=s;}

  @PostMapping
  public ResponseEntity<?> create(
      @RequestHeader(value="Authorization",required=false) String auth,
      @Valid @RequestBody PrescriptionRequest body){
    tokenService.validateBearer(auth);
    Prescription p = service.save(body);
    return ResponseEntity.status(201).body(p);
  }
}
