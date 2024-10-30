package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "roles")
public class Role {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nom_role")
        private String nomRole;

        @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
        private List<Utilisateur> utilisateurs;
}


