package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="caisses")
public class Caisse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaisse;

    @OneToMany
    @JoinColumn(name = "caisse_id")
    private List<SituationFinanciere> situationFinanciere;

    @Column(name="recette_du_jours")
    private Double recetteDuJours;

    @Column(name="recette_du_mois")
    private Double recetteDuMois;

    @Column(name="recette_de_annee")
    private Double recetteDeLAnnee;
}
