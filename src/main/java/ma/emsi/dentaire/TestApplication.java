package ma.emsi.dentaire;

import ma.emsi.dentaire.entities.*;
import ma.emsi.dentaire.enumeration.Assurance;
import ma.emsi.dentaire.enumeration.Mutuelle;
import ma.emsi.dentaire.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UtilisateurRepo utilisateurRepo, RoleRepo roleRepo){
        return args -> {
                Role r1 = new Role();
            if(roleRepo.findAll().isEmpty()){
                r1.setNomRole("Admin");
                roleRepo.save(r1);

                Role r2 = new Role();
                r2.setNomRole("Secrétaire");
                roleRepo.save(r2);

                Role r3 = new Role();
                r3.setNomRole("Dentiste");
                roleRepo.save(r3);
            }


            if(utilisateurRepo.findById(1L).isEmpty()){
                Utilisateur user = new Utilisateur();
                user.setNom("test");
                user.setPrenom("test");
                user.setCin("V000000");
                user.setMail("test@gmail.com");
                user.setRole(r1);
                user.setMotDePasse("test2024");
                user.setNomUtilisateur("test");
                user.setAdresse("Rue 20");
                user.setTelephone("0700000000");
                utilisateurRepo.save(user);
            }

        };
        }
}
