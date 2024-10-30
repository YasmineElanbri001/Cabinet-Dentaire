package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Acte;
import ma.emsi.dentaire.entities.InterventionMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepo extends JpaRepository<InterventionMedical, Long> {
}
