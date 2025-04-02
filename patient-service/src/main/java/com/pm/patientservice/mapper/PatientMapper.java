package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;


public class PatientMapper {

  public static PatientResponseDTO toDTO(Patient patient) {
    PatientResponseDTO patientDTO = new PatientResponseDTO();
    patientDTO.setId(patient.getId().toString());
    patientDTO.setName(patient.getName());
    patientDTO.setEmail(patient.getEmail());
    patientDTO.setAddress(patient.getAddress());
    patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
    return patientDTO;
  }

  private static Patient mapCommonFields(PatientRequestDTO patientRequestDTO, Patient patient) {
    patient.setName(patientRequestDTO.getName());
    patient.setEmail(patientRequestDTO.getEmail());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
    return patient;
  }

  public static Patient toModel(PatientRequestDTO patientRequestDTO) {
    Patient patient = new Patient();
    patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
    return mapCommonFields(patientRequestDTO, patient);
  }

  public static Patient toModel(PatientRequestDTO patientRequestDTO, Patient patient) {
    return mapCommonFields(patientRequestDTO, patient);
  }
}
