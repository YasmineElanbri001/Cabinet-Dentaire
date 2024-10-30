package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.Dentiste;
import ma.emsi.dentaire.services.DentisteService;
import ma.emsi.dentaire.services.DossierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dentistes")
public class DentisteController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "dentistes";
    }
    private DentisteService dentisteService;
    private DossierService dossierService;

    public DentisteController(DentisteService dentisteService, DossierService dossierService){

        this.dentisteService = dentisteService;
        this.dossierService = dossierService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_dentiste(Model dentisteModel){
        dentisteModel.addAttribute("dentiste",new Dentiste());
        return "dentiste/ajouter_dentiste";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_dentiste(Model dentisteModel,@Valid Dentiste dentiste, BindingResult  valResultat){
        if(valResultat.hasErrors() && (!dentiste.getMotDePasse().equals(dentiste.getConfirmPwd()))) {
            dentisteModel.addAttribute("dentiste",dentiste);
            dentisteModel.addAttribute("confirmer","non");
            return "dentiste/ajouter_dentiste";
        }else if(!valResultat.hasErrors() && (!dentiste.getMotDePasse().equals(dentiste.getConfirmPwd()))){
            dentisteModel.addAttribute("dentiste",dentiste);
            dentisteModel.addAttribute("confirmer","non");
            return "dentiste/ajouter_dentiste";
        }else if(valResultat.hasErrors() && (dentiste.getMotDePasse().equals(dentiste.getConfirmPwd()))){
            dentisteModel.addAttribute("confirmer","ok");
            dentisteModel.addAttribute("dentiste",dentiste);
            return "dentiste/ajouter_dentiste";
        }else{
            dentisteService.ajouterDentiste(dentiste);
            dentisteModel.addAttribute("dentiste",new Dentiste());
            dentisteModel.addAttribute("created","ok");
            return "dentiste/ajouter_dentiste";
        }
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_dentiste(Model dentisteModel,@RequestParam(name = "id") Long id){
        dentisteModel.addAttribute("dentiste",dentisteService.trouverDentiste(id));
        return "dentiste/edit_dentiste";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_dentiste(Model dentisteModel,@Valid Dentiste dentiste, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            dentisteModel.addAttribute("dentiste",dentiste);
            return "dentiste/edit_dentiste";
        }

        dentisteService.ajouterDentiste(dentiste);
        dentisteModel.addAttribute("created","ok");
        return "dentiste/edit_dentiste";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_dentiste(@RequestParam Long id, Model dentisteModel){
        try{
            dentisteService.supprimerDentiste(id);
        }catch(Exception e){

        }
        dentisteModel.addAttribute("dentistes",dentisteService.listDentistes());
        return "dentiste/list_dentistes";
    }

    // Liste des dentistes
    @GetMapping(path = "/liste")
    public String list_dentistes(Model dentisteModel){
        dentisteModel.addAttribute("dentistes",dentisteService.listDentistes());
        return "dentiste/list_dentistes";
    }

    // Liste des dossiers d'un dentiste
    @GetMapping(path = "/dossiers")
    public String list_dossiers_dentiste(@RequestParam Long id, Model dentisteModel){
        dentisteModel.addAttribute("dossiers",dossierService.dentisteDossiers(id));
        dentisteModel.addAttribute("dentiste", dentisteService.trouverDentiste(id));
        return "dentiste/dentiste_dossiers";
    }


}
