package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Acte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeRepo extends JpaRepository<Acte, Long> {
}
