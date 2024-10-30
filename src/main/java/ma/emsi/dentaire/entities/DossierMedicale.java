package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.StatutPaiement;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class DossierMedicale {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "dossierMedical", fetch = FetchType.EAGER)
    private List<Consultation> consultations;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreation;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;

    @NotNull
    @ManyToOne
    private Dentiste medcinTraitant;


    @NotBlank(message = "Veuillez saisir le numéro du dossier.")
    @Column(name = "numero_dossier")
    private String numeroDossier;

    @NotNull
    private StatutPaiement statutPaiement;

    @OneToOne
    private SituationFinanciere situationFinanciere;

}
