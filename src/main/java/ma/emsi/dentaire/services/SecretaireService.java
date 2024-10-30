package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Secretaire;
import ma.emsi.dentaire.repository.RoleRepo;
import ma.emsi.dentaire.repository.SecretaireRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretaireService {

    private SecretaireRepo secretaireRepo;
    private RoleRepo roleRepo;

    public SecretaireService(SecretaireRepo secretaireRepo, RoleRepo roleRepo){
        this.secretaireRepo = secretaireRepo;
        this.roleRepo = roleRepo;
    }

    public void ajouterSecretaire(Secretaire secretaire){
        secretaire.setRole(roleRepo.findById(2L).get());
        secretaireRepo.save(secretaire);
    }

    public void supprimerSecretaire(Long id){
        secretaireRepo.deleteById(id);

    }

    public Secretaire trouverSecretaire(Long id){
        return  secretaireRepo.findById(id).orElse(null);
    }

    public List<Secretaire> listSecretaires(){
        return secretaireRepo.findAll();
    }
}
