package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.InterventionMedical;
import ma.emsi.dentaire.services.ActeService;
import ma.emsi.dentaire.services.ConsultationService;
import ma.emsi.dentaire.services.InterventionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/interventions")
public class InterventionController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "interventions";
    }
    private InterventionService interventionService;
    private ActeService acteService;
    private ConsultationService consultationService;

    public InterventionController(InterventionService interventionService,
                                  ActeService acteService,
                                  ConsultationService consultationService){

        this.interventionService = interventionService;
        this.acteService= acteService;
        this.consultationService = consultationService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_intervention(Model interventionModel){
        interventionModel.addAttribute("interventionMedical",new InterventionMedical());
        interventionModel.addAttribute("actes",acteService.listActes());
        interventionModel.addAttribute("consultations",consultationService.listConsultations());
        return "intervention/ajouter_intervention";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_intervention(Model interventionModel,@Valid InterventionMedical interventionMedical, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            interventionModel.addAttribute("interventionMedical", interventionMedical);
            interventionModel.addAttribute("created", "non");
        }else{
            interventionService.ajouterIntervention(interventionMedical);
            interventionModel.addAttribute("interventionMedical", new InterventionMedical());
            interventionModel.addAttribute("created", "ok");
        }
        interventionModel.addAttribute("actes",acteService.listActes());
        interventionModel.addAttribute("consultations",consultationService.listConsultations());
        return "intervention/ajouter_intervention";
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_intervention(Model interventionModel,@RequestParam(name = "id") Long id){
        interventionModel.addAttribute("interventionMedical",interventionService.trouverIntervention(id));
        interventionModel.addAttribute("actes",acteService.listActes());
        interventionModel.addAttribute("consultations",consultationService.listConsultations());
        return "intervention/edit_intervention";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_intervention(Model interventionModel,@Valid InterventionMedical interventionMedical, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            interventionModel.addAttribute("interventionMedical", interventionMedical);
            interventionModel.addAttribute("created", "non");
        }else{
            interventionService.ajouterIntervention(interventionMedical);
            interventionModel.addAttribute("interventionMedical", new InterventionMedical());
            interventionModel.addAttribute("created", "ok");
        }

        interventionModel.addAttribute("actes",acteService.listActes());
        interventionModel.addAttribute("consultations",consultationService.listConsultations());
        return "intervention/edit_intervention";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_intervention(@RequestParam Long id, Model interventionModel){
        try{
            interventionService.supprimerIntervention(id);
        }catch(Exception e){

        }
        interventionModel.addAttribute("interventions",interventionService.listInterventions());
        return "intervention/list_interventions";
    }

    // Liste
    @GetMapping(path = "/liste")
    public String list_actes(Model interventionModel){
        interventionModel.addAttribute("interventions",interventionService.listInterventions());
        return "intervention/list_interventions";
    }


}
