package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Role;
import ma.emsi.dentaire.entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaireRepo extends JpaRepository<Secretaire, Long> {
}
