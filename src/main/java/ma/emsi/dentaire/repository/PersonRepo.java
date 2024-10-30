package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Personne, Long> {
}
