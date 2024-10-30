package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.TypeConsultation;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Consultation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdConsultation;

    @OneToMany(mappedBy = "consultation", fetch = FetchType.EAGER)
    private List<InterventionMedical> intervention;

    @NotNull(message = "Veuillez séléctionner le dossier médical")
    @ManyToOne
    private DossierMedicale dossierMedical;

    @NotNull(message = "Veuillez séléctionner la date de consultation")
    private LocalDate dateConsultation;

    @NotNull(message = "Veuillez séléctionner le type de consultation")
    @Enumerated(EnumType.STRING)
    private TypeConsultation typeConsultation;

    @OneToMany(mappedBy = "consultation", fetch = FetchType.EAGER)
    private List<Facture> factures;
}
