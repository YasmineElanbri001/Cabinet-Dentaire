package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Acte;
import ma.emsi.dentaire.entities.Dentiste;
import ma.emsi.dentaire.repository.ActeRepo;
import ma.emsi.dentaire.repository.DentisteRepo;
import ma.emsi.dentaire.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActeService {

    private ActeRepo acteRepo;

    public ActeService(ActeRepo acteRepo){
       this.acteRepo = acteRepo;
    }

    public void ajouterActe(Acte acte){
        acteRepo.save(acte);
    }

    public void supprimerActe(Long id){
        acteRepo.deleteById(id);

    }

    public Acte trouverActe(Long id){
        return  acteRepo.findById(id).orElse(null);
    }

    public List<Acte> listActes(){
        return acteRepo.findAll();
    }
}
