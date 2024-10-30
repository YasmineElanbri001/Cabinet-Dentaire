package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {
}
