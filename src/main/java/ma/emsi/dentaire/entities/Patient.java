package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.dentaire.enumeration.GroupeSanguin;
import ma.emsi.dentaire.enumeration.Mutuelle;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name="patients")
public class Patient extends Personne{

        @NotNull
        @Temporal(TemporalType.DATE)
        @Column(name="date_naissance")
        private LocalDate dateNaissance;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Mutuelle mutuelle;

        @NotNull
        @Column(name="groupe_sanguin")
        private GroupeSanguin groupSanguin;

        @ManyToMany(mappedBy = "patients")
        private List<AntecedentMedical> AntecedentMedicaux;

        @NotBlank(message = "Vueillez saisir la profession du patient")
        private String profession;

        @OneToOne(mappedBy = "patient")
        private DossierMedicale dossierMedical;
}
