package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="utilisateurs")
public class Utilisateur extends Personne {

    @NotNull(message = "Veuillez saisir le nom d'utilisateur.")
    @Size(min=2,max=100, message = "La taille doit être entre 2 et 100 caractères.")
    private String nomUtilisateur;

    @NotNull(message = "Veuillez saisir le mot de passe.")
    @Size(min=8,max=100,message = "La taille doit être entre 8 et 100 caractères.")
    private String motDePasse;

    @Transient
    private String confirmPwd;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}