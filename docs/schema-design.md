# MySQL Schema — Smart Clinic

## Tables

### users
- id BIGINT PK AUTO_INCREMENT
- email VARCHAR(255) UNIQUE NOT NULL
- password_hash VARCHAR(255) NOT NULL
- role ENUM('ADMIN','DOCTOR','PATIENT') NOT NULL
- created_at DATETIME DEFAULT CURRENT_TIMESTAMP

### patients
- id BIGINT PK AUTO_INCREMENT
- user_id BIGINT NOT NULL UNIQUE REFERENCES users(id)
- full_name VARCHAR(120) NOT NULL
- phone VARCHAR(20) UNIQUE NOT NULL
- dob DATE NULL

### doctors
- id BIGINT PK AUTO_INCREMENT
- user_id BIGINT NOT NULL UNIQUE REFERENCES users(id)
- full_name VARCHAR(120) NOT NULL
- specialty VARCHAR(120) NOT NULL
- bio TEXT NULL
- created_at DATETIME DEFAULT CURRENT_TIMESTAMP

### doctor_available_times
- doctor_id BIGINT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE
- slot_time TIME NOT NULL
- PRIMARY KEY (doctor_id, slot_time)

### appointments
- id BIGINT PK AUTO_INCREMENT
- doctor_id BIGINT NOT NULL REFERENCES doctors(id)
- patient_id BIGINT NOT NULL REFERENCES patients(id)
- appointment_time DATETIME NOT NULL
- status ENUM('BOOKED','CANCELLED','DONE') NOT NULL DEFAULT 'BOOKED'
- INDEX (doctor_id, appointment_time)

### prescriptions
- id BIGINT PK AUTO_INCREMENT
- appointment_id BIGINT NOT NULL UNIQUE REFERENCES appointments(id)
- medication TEXT NOT NULL
- dosage TEXT NOT NULL
- notes TEXT NULL
- created_at DATETIME DEFAULT CURRENT_TIMESTAMP

## Relationships
- users 1—1 patients; users 1—1 doctors
- doctors 1—N doctor_available_times
- doctors N—N patients through appointments
- appointments 1—1 prescriptions
