package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationFinanciereRepo extends JpaRepository<SituationFinanciere, Long> {
}
