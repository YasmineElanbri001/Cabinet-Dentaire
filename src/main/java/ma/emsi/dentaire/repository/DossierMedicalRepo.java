package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.DossierMedicale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepo extends JpaRepository<DossierMedicale, Long> {
    List<DossierMedicale> findByMedcinTraitantId(Long id);
}
