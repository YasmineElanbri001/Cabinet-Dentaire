package ma.emsi.dentaire.repository;

import ma.emsi.dentaire.entities.Personne;
import ma.emsi.dentaire.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
