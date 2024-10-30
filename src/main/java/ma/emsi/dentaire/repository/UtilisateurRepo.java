package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Secretaire;
import ma.emsi.dentaire.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
}
