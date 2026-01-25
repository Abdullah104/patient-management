package com.pm.patient_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registeredDate;

    public static final class PatientBuilder {
        private UUID id;
        private String name;
        private String email;
        private String address;
        private LocalDate dateOfBirth;
        private LocalDate registeredDate;

        public Patient build() {
            var patient = new Patient();
            patient.setId(id);
            patient.setName(name);
            patient.setEmail(email);
            patient.setAddress(address);
            patient.setDateOfBirth(dateOfBirth);
            patient.setRegisteredDate(registeredDate);

            return patient;
        }

        public PatientBuilder id(UUID id) {
            this.id = id;

            return this;
        }

        public PatientBuilder name(String name) {
            this.name = name;

            return this;
        }

        public PatientBuilder email(String email) {
            this.email = email;

            return this;
        }

        public PatientBuilder address(String address) {
            this.address = address;

            return this;
        }

        public PatientBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;

            return this;
        }

        public PatientBuilder registeredDate(LocalDate registeredDate) {
            this.registeredDate = registeredDate;

            return this;
        }
    }
}
