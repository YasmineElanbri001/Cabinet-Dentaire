package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.Assurance;
import ma.emsi.dentaire.enumeration.Specialite;
import ma.emsi.dentaire.enumeration.StatusEmploye;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="dentistes")
public class Dentiste extends Utilisateur {

        @Column(name = "date_retour_conge")
        private LocalDate dateRetourConge;

        @NotNull(message = "Veuillez saisir le salaire de base")
        @DecimalMin(value = "0.0", inclusive = false, message = "Le champ doit être supérieur à zéro")
        @Column(name = "salaire_de_base")
        private Double salaireDeBase;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Specialite specialite;


        @ManyToMany(mappedBy = "dentistes")
        private List<Disponibilite> disponi;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Assurance assurance;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "status_actuel")
        private StatusEmploye statusActuel;

        @OneToMany(mappedBy = "medcinTraitant", fetch = FetchType.EAGER)
        private List<DossierMedicale> dossierMedicale;

}
