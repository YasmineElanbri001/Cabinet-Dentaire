package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.Acte;
import ma.emsi.dentaire.services.ActeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actes")
public class ActeController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "actes";
    }
    private ActeService acteService;

    public ActeController(ActeService acteService){
        this.acteService = acteService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_acte(Model acteModel){
        acteModel.addAttribute("acte",new Acte());
        return "acte/ajouter_acte";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_acte(Model acteModel,@Valid Acte acte, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            acteModel.addAttribute("acte", acte);
            acteModel.addAttribute("created", "non");
        }else{
            acteService.ajouterActe(acte);
            acteModel.addAttribute("acte", new Acte());
            acteModel.addAttribute("created", "ok");
        }
        return "acte/ajouter_acte";
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_acte(Model acteModel,@RequestParam(name = "id") Long id){
        acteModel.addAttribute("acte",acteService.trouverActe(id));
        return "acte/edit_acte";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_acte(Model acteModel,@Valid Acte acte, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            acteModel.addAttribute("acte", acte);
            acteModel.addAttribute("created", "non");
        }else{
            acteService.ajouterActe(acte);
            acteModel.addAttribute("acte", new Acte());
            acteModel.addAttribute("created", "ok");
        }
        return "acte/edit_acte";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_acte(@RequestParam Long id, Model acteModel){
        acteService.supprimerActe(id);
        acteModel.addAttribute("actes",acteService.listActes());
        return "acte/list_actes";
    }

    // Liste
    @GetMapping(path = "/liste")
    public String list_actes(Model acteModel){
        acteModel.addAttribute("actes",acteService.listActes());
        return "acte/list_actes";
    }


}
