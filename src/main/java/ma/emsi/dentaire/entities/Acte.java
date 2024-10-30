package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.CategorieActe;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Acte {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActe;

    @OneToMany(mappedBy = "acte", fetch = FetchType.EAGER)
    private List<InterventionMedical> intervention;

    @NotNull(message = "Veuillez saisir le prix de base")
    @DecimalMin(value = "1")
    @Column(name="prix_de_base")
    private Double prixDeBase;


    @NotNull(message = "Veuillez sélectionner la calégorie.")
    private CategorieActe categorie;

    @NotBlank(message = "Vuillez saisir le libélle")
    private String libelle;
}
