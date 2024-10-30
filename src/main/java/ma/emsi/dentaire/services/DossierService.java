package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.DossierMedicale;
import ma.emsi.dentaire.entities.Patient;
import ma.emsi.dentaire.repository.DossierMedicalRepo;
import ma.emsi.dentaire.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierService {

    private DossierMedicalRepo dossierMedicalRepo;

    public DossierService(DossierMedicalRepo dossierMedicalRepo){

        this.dossierMedicalRepo = dossierMedicalRepo;
    }

    public void ajouterDossier(DossierMedicale dossier){
        dossierMedicalRepo.save(dossier);
    }

    public void supprimerDossier(Long id){
        dossierMedicalRepo.deleteById(id);
    }

    public DossierMedicale trouverDossier(Long id){

        return  dossierMedicalRepo.findById(id).orElse(null);
    }

    public List<DossierMedicale> listDossiers(){
        return dossierMedicalRepo.findAll();
    }

    public List<DossierMedicale> dentisteDossiers(Long id){
        return dossierMedicalRepo.findByMedcinTraitantId(id);
    }
}
