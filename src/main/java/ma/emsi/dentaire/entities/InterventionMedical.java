package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class InterventionMedical {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntervention;

    @NotNull(message = "Veuillez saisir le prix.")
    @DecimalMin(value = "1", message = "Veuillez saisir une valeur valide.")
    @Column(name="prix_patient")
    private Double prixPatient;

    @NotNull(message = "Veuillez saisir le prix.")
    @Min(value = 1, message = "Veuillez saisir une valeur valide.")
    private Long dent;

    @NotNull(message = "Veuillez sélectionner une consultation.")
    @ManyToOne
    private Consultation consultation;

    @NotNull(message = "Veuillez sélectionner un acte.")
    @ManyToOne
    private Acte acte;

    @NotBlank(message = "Veuillez saisir la note médicale.")
    private String note;


}
