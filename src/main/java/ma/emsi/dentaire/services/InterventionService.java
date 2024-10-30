package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Acte;
import ma.emsi.dentaire.entities.InterventionMedical;
import ma.emsi.dentaire.repository.ActeRepo;
import ma.emsi.dentaire.repository.InterventionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    private InterventionRepo interventionRepo;

    public InterventionService(InterventionRepo interventionRepo){

        this.interventionRepo = interventionRepo;
    }

    public void ajouterIntervention(InterventionMedical interventionMedical){
        interventionRepo.save(interventionMedical);
    }

    public void supprimerIntervention(Long id){
        interventionRepo.deleteById(id);

    }

    public InterventionMedical trouverIntervention(Long id){

        return  interventionRepo.findById(id).orElse(null);
    }

    public List<InterventionMedical> listInterventions(){

        return interventionRepo.findAll();
    }
}
