# User Stories — Smart Clinic

## Doctor

### D1. Manage Availability
**As a** Doctor  
**I want** to define my available time slots  
**So that** patients can book appointments only when I’m free.

**Acceptance Criteria**
- Given I am authenticated as a doctor, when I open “My Availability” I can add/remove time slots (HH:MM).
- Only future slots are allowed.
- Saved slots are visible to patients in search results.

**Priority:** High · **Story Points:** 3

### D2. View Upcoming Appointments
**As a** Doctor  
**I want** to see my appointments by day  
**So that** I can prepare for each patient.

**Acceptance Criteria**
- List shows patient name, phone, time, status.
- Filter by date (default today).
- Sorted by time ascending.

**Priority:** High · **Story Points:** 2

### D3. Add Prescription
**As a** Doctor  
**I want** to record a prescription for an appointment  
**So that** the patient can view the treatment plan.

**Acceptance Criteria**
- POST `/api/prescriptions` with `appointmentId, medication, dosage, notes`.
- Returns 201 with JSON body of created prescription.
- Appointment status may remain BOOKED/DONE (no hard dependency).

**Priority:** Medium · **Story Points:** 3

---

## Patient

### P1. Search Doctor
**As a** Patient  
**I want** to search doctors by name/specialty and see next available time  
**So that** I can find a suitable doctor quickly.

**Acceptance Criteria**
- GET `/api/doctors?name={q}&specialty={s}&date=YYYY-MM-DD`.
- Response includes doctor id, fullName, specialty, availableTimes for that date.

**Priority:** High · **Story Points:** 3

### P2. Book/Cancel Appointment
**As a** Patient  
**I want** to book and cancel my appointments  
**So that** I can manage my schedule.

**Acceptance Criteria**
- POST `/api/appointments` with `doctorId, dateTime`.
- Prevent double booking.
- PATCH `/api/appointments/{id}/cancel` sets status=CANCELLED if >2h before.

**Priority:** High · **Story Points:** 5

### P3. View Prescriptions
**As a** Patient  
**I want** to see my prescriptions  
**So that** I can follow my treatment.

**Acceptance Criteria**
- GET `/api/prescriptions/mine` returns list with `medication, dosage, notes, createdAt`.

**Priority:** Medium · **Story Points:** 2

---

## Admin

### A1. Manage Doctors
**As an** Admin  
**I want** to create/update/disable doctor accounts  
**So that** I can manage staff access.

**Acceptance Criteria**
- Create doctor with `fullName, specialty, email, phone`.
- Toggle active flag; disabled doctors can’t log in.
- Duplicate emails are rejected.

**Priority:** High · **Story Points:** 5

### A2. Account Lock/Unlock
**As an** Admin  
**I want** to lock/unlock user accounts  
**So that** I can secure the system.

**Acceptance Criteria**
- Lock prevents login JWT issuance.
- Audit entry recorded (who locked, when).

**Priority:** Medium · **Story Points:** 2

### A3. Daily Appointment Report
**As an** Admin  
**I want** a daily appointment report per doctor  
**So that** I can monitor activity.

**Acceptance Criteria**
- Procedure returns rows: `doctor_name, appointment_time, status, patient_name, patient_phone`, ordered by `doctor_name, appointment_time`.
- Date provided as parameter `YYYY-MM-DD`.

**Priority:** Medium · **Story Points:** 3
