package ma.emsi.dentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="personnes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Size(min=2,max=100, message = "La taille doit être entre 2 et 100 caractères.")
        private String nom;

        @NotNull(message = "Veuillez saisir le prénom.")
        @Size(min=2,max=100,message = "La taille doit être entre 2 et 100 caractères.")
        private String prenom;


        @Size(min=4,max=100,message = "Adresse invalide")
        private String adresse;

        @NotNull
        @Size(min=8,max=100,message = "Veuillez saisir un numéro de téléphone valide")
        private String telephone;

        @NotBlank(message = "Veuillez saisir un email.")
        @Email(message = "Veuillez saisir un email valide.")
        private String mail;

        @NotNull(message = "Veuillez saisir le CIN.")
        @Size(min=4,max=100,message = "CIN invalide")
        private String cin;

    }


