package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.CategorieAM;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class AntecedentMedical {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedent;

    @ManyToMany
    private List<Patient> patients;

    private String libelle;

    @Enumerated(EnumType.STRING)
    private CategorieAM categorie;
}
