package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Dentiste;
import ma.emsi.dentaire.repository.RoleRepo;
import ma.emsi.dentaire.repository.DentisteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentisteService {

    private DentisteRepo dentisteRepo;
    private RoleRepo roleRepo;

    public DentisteService(DentisteRepo dentisteRepo, RoleRepo roleRepo){
        this.dentisteRepo = dentisteRepo;
        this.roleRepo = roleRepo;
    }

    public void ajouterDentiste(Dentiste dentiste){
        dentiste.setRole(roleRepo.findById(3L).get());
        dentisteRepo.save(dentiste);
    }

    public void supprimerDentiste(Long id){
        dentisteRepo.deleteById(id);

    }

    public Dentiste trouverDentiste(Long id){
        return  dentisteRepo.findById(id).orElse(null);
    }

    public List<Dentiste> listDentistes(){
        return dentisteRepo.findAll();
    }
}
