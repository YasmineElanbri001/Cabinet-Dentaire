package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Disponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jour;

    private String dispo;

    @ManyToMany
    private List<Dentiste> dentistes;

    //private Map<DayOfWeek, Disponibilite> disponibilite;
}
