package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SituationFinanciere {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSituationFinanciere;

    @Column(name="date_creation")
    private LocalDate dateCreation;

    @Column(name="montant_globale_restant")
    private Double montantGlobaleRestant;

    @Column(name="montant_globale_paye")
    private Double montantGlobalePaye;

    @OneToOne
    @JoinColumn(name="dossier_medical")
    private DossierMedicale dossierMedicale;

    @OneToMany(mappedBy = "situationFinanciere")
    private List<Facture> factures;

    public void calculerMontants() {
        double totalPaye = factures.stream().mapToDouble(Facture::getMontantPaye).sum();
        double totalReste = factures.stream().mapToDouble(Facture::getMontantTotal).sum() - totalPaye;

        this.montantGlobalePaye = totalPaye;
        this.montantGlobaleRestant = totalReste;
    }

}
