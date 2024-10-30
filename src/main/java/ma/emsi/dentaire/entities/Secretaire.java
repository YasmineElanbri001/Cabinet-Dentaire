package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.Assurance;
import ma.emsi.dentaire.enumeration.StatusEmploye;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "secretaires")
public class Secretaire extends Utilisateur {

        @NotNull(message = "Veuillez saisir le salaire de base")
        @DecimalMin(value = "0.0", inclusive = false, message = "Le champ doit être supérieur à zéro")
        @Column(name = "salaire_de_base")
        private Double salaireDeBase;


        @Column(name = "date_retour_conge")
        private LocalDate dateRetourConge;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Assurance assurance;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "status_actuel")
        private StatusEmploye statusActuel;

        @NotNull(message = "Vuillez saisir une valeur.")
        @Min(value = 0, message = "Le salaire doit être supérieur à 0.")
        private Double prime;

    }


