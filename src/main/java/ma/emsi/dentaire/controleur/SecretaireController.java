package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.Secretaire;
import ma.emsi.dentaire.services.SecretaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/secretaires")
public class SecretaireController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "secretaires";
    }
    private SecretaireService secretaireService;

    public SecretaireController(SecretaireService secretaireService){
        this.secretaireService = secretaireService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_secretaire(Model secretaireModel){
        secretaireModel.addAttribute("secretaire",new Secretaire());
        return "secretaire/ajouter_secretaire";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_secretaire(Model secretaireModel,@Valid Secretaire secretaire, BindingResult  valResultat){
        if(valResultat.hasErrors() && (!secretaire.getMotDePasse().equals(secretaire.getConfirmPwd()))) {
            secretaireModel.addAttribute("secretaire",secretaire);
            secretaireModel.addAttribute("confirmer","non");
            return "secretaire/ajouter_secretaire";
        }else if(!valResultat.hasErrors() && (!secretaire.getMotDePasse().equals(secretaire.getConfirmPwd()))){
            secretaireModel.addAttribute("secretaire",secretaire);
            secretaireModel.addAttribute("confirmer","non");
            return "secretaire/ajouter_secretaire";
        }else if(valResultat.hasErrors() && (secretaire.getMotDePasse().equals(secretaire.getConfirmPwd()))){
            secretaireModel.addAttribute("confirmer","ok");
            secretaireModel.addAttribute("secretaire",secretaire);
            return "secretaire/ajouter_secretaire";
        }else{
            secretaireService.ajouterSecretaire(secretaire);
            secretaireModel.addAttribute("secretaire",new Secretaire());
            secretaireModel.addAttribute("created","ok");
            return "secretaire/ajouter_secretaire";
        }
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_secretaire(Model secretaireModel,@RequestParam(name = "id") Long id){
        secretaireModel.addAttribute("secretaire",secretaireService.trouverSecretaire(id));
        return "secretaire/edit_secretaire";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_secretaire(Model secretaireModel,@Valid Secretaire secretaire, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            secretaireModel.addAttribute("secretaire",secretaire);
            return "secretaire/edit_secretaire";
        }

        secretaireService.ajouterSecretaire(secretaire);
        secretaireModel.addAttribute("created","ok");
        return "secretaire/edit_secretaire";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_secretaire(@RequestParam Long id, Model secretaireModel){
        secretaireService.supprimerSecretaire(id);
        secretaireModel.addAttribute("secretaires",secretaireService.listSecretaires());
        return "secretaire/list_secretaires";
    }

    // Liste
    @GetMapping(path = "/liste")
    public String list_secretaires(Model secretaireModel){
        secretaireModel.addAttribute("secretaires",secretaireService.listSecretaires());
        return "secretaire/list_secretaires";
    }


}
