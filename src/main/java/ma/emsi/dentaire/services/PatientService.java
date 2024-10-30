package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Patient;
import ma.emsi.dentaire.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public void ajouterPatient(Patient patient){
        patientRepo.save(patient);
    }

    public void supprimerPatient(Long id){
        patientRepo.deleteById(id);

    }

    public Patient trouverPatient(Long id){
        return  patientRepo.findById(id).orElse(null);
    }

    public List<Patient> listPatients(){
        return patientRepo.findAll();
    }

    public List<Patient> patientsSansDossierMedical(){
        return patientRepo.findByDossierMedicalIsNull();
    }
}
