package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.TypePaiement;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="factures")
public class Facture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;

    @NotNull(message = "Taper le montant restant.")
    @DecimalMin(value = "0", message = "Veuillez saisir une valeur valide.")
    @Column(name="montant_restant")
    private Double montantRestant;

    @ManyToOne
    private SituationFinanciere situationFinanciere;

    @NotNull(message = "Taper le montant payé.")
    @DecimalMin(value = "0", message = "Veuillez saisir une valeur valide.")
    @Column(name="montant_paye")
    private Double montantPaye;

    @NotNull(message = "Veuillez sélectionner la date de paiement.")
    @Column(name="date_facturation")
    private LocalDate dateFacturation;

    @NotNull(message = "Taper le montant total.")
    @DecimalMin(value = "0", message = "Veuillez saisir une valeur valide.")
    @Column(name="montant_total")
    private Double montantTotal;

    @NotNull(message = "Veuillez sélectionner la consultation.")
    @ManyToOne
    private Consultation consultation;

    @NotNull(message = "Veuillez sélectionner le type du paiement.")
    @Enumerated(EnumType.STRING)
    @Column(name="type_paiement")
    private TypePaiement typePaiement;
}
