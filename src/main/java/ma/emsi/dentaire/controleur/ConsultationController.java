package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.Patient;
import ma.emsi.dentaire.services.ConsultationService;
import ma.emsi.dentaire.services.DossierService;
import ma.emsi.dentaire.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/consultations")
public class ConsultationController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "consultations";
    }
    private ConsultationService consultationService;
    private DossierService dossierService;
    private PatientService patientService;

    public ConsultationController(ConsultationService consultationService, DossierService dossierService, PatientService patientService){

        this.consultationService = consultationService;
        this.dossierService = dossierService;
        this.patientService = patientService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_consultation(Model consultationModel){
        consultationModel.addAttribute("consultation",new Consultation());
        consultationModel.addAttribute("dossiers", dossierService.listDossiers());
        return "consultation/ajouter_consultation";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_consultation(Model consultationModel,@Valid Consultation consultation, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            consultationModel.addAttribute("consultation",consultation);
            consultationModel.addAttribute("created","non");
        }else{
            consultationService.ajouterConsultation(consultation);
            consultationModel.addAttribute("consultation",new Consultation());
            consultationModel.addAttribute("created","ok");
        }
        consultationModel.addAttribute("dossiers", dossierService.listDossiers());
        return "consultation/ajouter_consultation";
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_consultation(Model consultationModel,@RequestParam(name = "id") Long id){
        consultationModel.addAttribute("consultation",consultationService.trouverConsultation(id));
        return "consultation/edit_consultation";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_consultation(Model consultationModel,Consultation consultation, BindingResult  valResultat){
        consultationService.ajouterConsultation(consultation);
        consultationModel.addAttribute("created","ok");
        return "consultation/edit_consultation";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_consultation(@RequestParam Long id, Model consultationModel){
        consultationService.supprimerConsultation(id);
        consultationModel.addAttribute("consultations",consultationService.listConsultations());
        return "consultation/list_consultations";
    }

    // Liste des consultaions
    @GetMapping(path = "/liste")
    public String list_consultations(Model consultationModel){
        consultationModel.addAttribute("consultations",consultationService.listConsultations());
        return "consultation/list_consultations";
    }


    // Liste des consultaions d'un patient
    @GetMapping(path = "/patient")
    public String patient_consultations(Model consultationModel, @RequestParam(name="id") Long id){
        Patient patient= new Patient();
        patient = patientService.trouverPatient(id);
        if(patient.getDossierMedical() !=null && patient.getDossierMedical().getConsultations() != null){
            consultationModel.addAttribute("consultations",patient.getDossierMedical().getConsultations());
        }else{
            consultationModel.addAttribute("consultations",new ArrayList<>());
        }
        consultationModel.addAttribute("patient",patient);
        return "consultation/patient_consultations";
    }
}
