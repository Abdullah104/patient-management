package com.pm.patientservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
data class Patient(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: String,
    @NotNull var name: String,
    @NotNull @Email @Column(unique = true) var email: String,
    @NotNull var address: String,
    @NotNull var dateOfBirth: LocalDate,
    @NotNull var registeredDate: LocalDate
)
