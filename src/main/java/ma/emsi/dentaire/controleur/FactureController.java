package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.*;
import ma.emsi.dentaire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/factures")
public class FactureController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "factures";
    }
    private ConsultationService consultationService;
    private FactureService factureService;
    private SituationFinanciereService situationFinanciereService;
    private DossierService dossierService;

    public FactureController(ConsultationService consultationService,
                             FactureService factureService,
                             SituationFinanciereService situationFinanciereService,
                             DossierService dossierService){

        this.consultationService = consultationService;
        this.factureService = factureService;
        this.situationFinanciereService = situationFinanciereService;
        this.dossierService = dossierService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_facture(Model factureModel){
        factureModel.addAttribute("facture",new Facture());
        factureModel.addAttribute("consultations", consultationService.listConsultations());
        factureModel.addAttribute("situations", situationFinanciereService.listSituations());
        return "facture/ajouter_facture";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_facture(Model factureModel,@Valid Facture facture, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            factureModel.addAttribute("facture",facture);
            factureModel.addAttribute("created","non");
        }else{
            facture.setSituationFinanciere(facture.getConsultation().getDossierMedical().getSituationFinanciere());
            factureService.ajouterFacture(facture);
            factureModel.addAttribute("facture",new Facture());
            factureModel.addAttribute("created","ok");
        }
        factureModel.addAttribute("consultations", consultationService.listConsultations());
        factureModel.addAttribute("situations", situationFinanciereService.listSituations());
        return "facture/ajouter_facture";
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_facture(Model factureModel,@RequestParam(name = "id") Long id){
        factureModel.addAttribute("facture",factureService.trouverFacture(id));
        factureModel.addAttribute("consultations", consultationService.listConsultations());
        factureModel.addAttribute("situations", situationFinanciereService.listSituations());
        return "facture/edit_facture";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_facture(Model factureModel,@Valid Facture facture, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            factureModel.addAttribute("facture",facture);
        }else{
            factureService.ajouterFacture(facture);
            factureModel.addAttribute("created","ok");
        }
        return "facture/edit_facture";
    }


    // Liste des consultaions
    @GetMapping(path = "/liste")
    public String list_factures(Model factureModel){
        factureModel.addAttribute("factures",factureService.listFacturs());
        return "facture/list_factures";
    }


    // Liste des factures d'un patient
    @GetMapping(path = "/dossier")
    public String dossier_factures(Model factureModel, @RequestParam(name="id") Long id){
        DossierMedicale dm = new DossierMedicale();
        dm = dossierService.trouverDossier(id);
        factureModel.addAttribute("factures",dm.getSituationFinanciere().getFactures());
        factureModel.addAttribute("dossier",dm);
        return "facture/dossier_factures";
    }
}
