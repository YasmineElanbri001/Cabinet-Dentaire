package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.DossierMedicale;
import ma.emsi.dentaire.entities.SituationFinanciere;
import ma.emsi.dentaire.services.DentisteService;
import ma.emsi.dentaire.services.DossierService;
import ma.emsi.dentaire.services.PatientService;
import ma.emsi.dentaire.services.SituationFinanciereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/dossiers")
public class DossierController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "dossiers";
    }
    private DossierService dossierService;
    private PatientService patientService;
    private DentisteService dentisteService;
    private SituationFinanciereService situationFinanciereService;

    public DossierController(DossierService dossierService,
                             PatientService patientService,
                             DentisteService dentisteService,
                             SituationFinanciereService situationFinanciereService){

        this.dossierService = dossierService;
        this.patientService = patientService;
        this.dentisteService = dentisteService;
        this.situationFinanciereService = situationFinanciereService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_dossier(Model dossierModel){
        dossierModel.addAttribute("dossierMedicale",new DossierMedicale());
        dossierModel.addAttribute("patients", patientService.patientsSansDossierMedical());
        dossierModel.addAttribute("dentistes", dentisteService.listDentistes());
        return "dossier/ajouter_dossier";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_dossier(Model dossierModel,@Valid DossierMedicale dossier, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            dossierModel.addAttribute("dossierMedicale",dossier);
            dossierModel.addAttribute("created","non");
        }else{
            dossierService.ajouterDossier(dossier);
            // Création de situation financiere
            SituationFinanciere sf = new SituationFinanciere();
            sf.setDateCreation(LocalDate.now());
            sf.setDossierMedicale(dossier);
            situationFinanciereService.ajouterSituation(sf);
            dossier.setSituationFinanciere(sf);
            dossierService.ajouterDossier(dossier);
            dossierModel.addAttribute("dossierMedicale",new DossierMedicale());
            dossierModel.addAttribute("created","ok");
        }
        dossierModel.addAttribute("patients", patientService.patientsSansDossierMedical());
        dossierModel.addAttribute("dentistes", dentisteService.listDentistes());
        return "dossier/ajouter_dossier";
    }


    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_dossier(Model dossierModel,@RequestParam(name = "id") Long id){
        DossierMedicale dm = new DossierMedicale();
        dm = dossierService.trouverDossier(id);
        dossierModel.addAttribute("dossierMedicale",dm);
        dossierModel.addAttribute("patient", patientService.trouverPatient(dm.getPatient().getId()));
        dossierModel.addAttribute("dentiste", dentisteService.trouverDentiste(dm.getMedcinTraitant().getId()));
        return "dossier/edit_dossier";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_dossier(Model dossierModel, DossierMedicale dossier){
        dossierService.ajouterDossier(dossier);
        dossierModel.addAttribute("patient", patientService.trouverPatient(dossier.getPatient().getId()));
        dossierModel.addAttribute("dentiste", dentisteService.trouverDentiste(dossier.getMedcinTraitant().getId()));
        dossierModel.addAttribute("created","ok");
        return "dossier/edit_dossier";
    }


    // Liste
    @GetMapping(path = "/liste")
    public String list_dossiers(Model dossierModel){
        dossierModel.addAttribute("dossiers",dossierService.listDossiers());
        return "dossier/list_dossiers";
    }
}
