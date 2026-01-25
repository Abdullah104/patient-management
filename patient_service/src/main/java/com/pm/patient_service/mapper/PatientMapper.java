package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.model.Patient.PatientBuilder;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        return new PatientResponseDTO(patient.getId().toString(), patient.getName(), patient.getEmail(),
                patient.getAddress(), patient.getDateOfBirth().toString());
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        return new PatientBuilder()
                .name(patientRequestDTO.getName())
                .email(patientRequestDTO.getEmail())
                .address(patientRequestDTO.getAddress())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()))
                .registeredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()))
                .build();
    }
}
