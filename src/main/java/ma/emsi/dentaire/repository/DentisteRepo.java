package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Dentiste;
import ma.emsi.dentaire.entities.DossierMedicale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentisteRepo extends JpaRepository<Dentiste, Long> {

}
