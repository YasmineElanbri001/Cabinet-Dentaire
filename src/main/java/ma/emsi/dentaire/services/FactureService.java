package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.Facture;
import ma.emsi.dentaire.entities.SituationFinanciere;
import ma.emsi.dentaire.repository.ConsultationRepo;
import ma.emsi.dentaire.repository.FactureRepo;
import ma.emsi.dentaire.repository.SituationFinanciereRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    private FactureRepo factureRepo;
    private SituationFinanciereRepo situationFinanciereRepo;

    public FactureService(FactureRepo factureRepo, SituationFinanciereRepo situationFinanciereRepo){

        this.factureRepo = factureRepo;
        this.situationFinanciereRepo = situationFinanciereRepo;
    }

    public void ajouterFacture(Facture facture){

        Facture f = factureRepo.save(facture);
        SituationFinanciere sf = f.getSituationFinanciere();
        sf.calculerMontants();
        situationFinanciereRepo.save(sf);

    }


    public Facture trouverFacture(Long id){
        return  factureRepo.findById(id).orElse(null);
    }

    public List<Facture> listFacturs(){
        return factureRepo.findAll();
    }
}
