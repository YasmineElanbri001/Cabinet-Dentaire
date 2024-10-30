package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.SituationFinanciere;
import ma.emsi.dentaire.repository.ConsultationRepo;
import ma.emsi.dentaire.repository.SituationFinanciereRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituationFinanciereService {

    private final SituationFinanciereRepo situationFinanciereRepo;

    public SituationFinanciereService(SituationFinanciereRepo situationFinanciereRepo){
        this.situationFinanciereRepo = situationFinanciereRepo;
    }

    public void ajouterSituation(SituationFinanciere situationFinanciere){

        situationFinanciereRepo.save(situationFinanciere);
    }

    public SituationFinanciere trouverSituation(Long id){

        return  situationFinanciereRepo.findById(id).orElse(null);
    }

    public List<SituationFinanciere> listSituations(){

        return situationFinanciereRepo.findAll();
    }
}
