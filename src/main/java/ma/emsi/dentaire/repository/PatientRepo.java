package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByDossierMedicalIsNull();
}
