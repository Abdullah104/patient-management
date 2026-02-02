package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        final String email = patientRequestDTO.getEmail();

        if (patientRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + email);
        }

        return PatientMapper.toDTO(patientRepository.save(PatientMapper.toModel(patientRequestDTO)));
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        var patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(
                "Patient not found with id: " + id));

        var email = patientRequestDTO.getEmail();
        if (patientRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + email);
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        var updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);
    }
}
