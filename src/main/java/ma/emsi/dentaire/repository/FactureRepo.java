package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepo extends JpaRepository<Facture, Long> {
}
