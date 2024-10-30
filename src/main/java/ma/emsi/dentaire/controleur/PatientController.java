package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ma.emsi.dentaire.entities.Patient;
import ma.emsi.dentaire.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "patients";
    }
    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    // Formulaire d'ajout
    @GetMapping(path = "/ajouter")
    public String form_ajouter_patient(Model patientModel){
        patientModel.addAttribute("patient",new Patient());
        return "patient/ajouter_patient";
    }

    // Ajouter
    @PostMapping(path = "/ajouter")
    public String ajouter_patient(Model patientModel,@Valid Patient patient, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            patientModel.addAttribute("patient",patient);
            patientModel.addAttribute("created","non");
        }else{
            patientService.ajouterPatient(patient);
            patientModel.addAttribute("patient",new Patient());
            patientModel.addAttribute("created","ok");
        }
        return "patient/ajouter_patient";
    }

    // Formulaire de mise à jour
    @GetMapping(path = "/edit")
    public String form_edit_patient(Model patientModel,@RequestParam(name = "id") Long id){
        patientModel.addAttribute("patient",patientService.trouverPatient(id));
        return "patient/edit_patient";
    }

    // Mise à jour
    @PostMapping(path = "/edit")
    public String edit_patient(Model patientModel,@Valid Patient patient, BindingResult  valResultat){
        if(valResultat.hasErrors()) {
            patientModel.addAttribute("patient",patient);
            return "patient/edit_patient";
        }
        patientService.ajouterPatient(patient);
        patientModel.addAttribute("created","ok");
        return "patient/edit_patient";
    }

    // Supprimer
    @GetMapping(path = "/supprimer")
    public String supp_patient(@RequestParam Long id, Model patientModel){
        try{
            patientService.supprimerPatient(id);
        }catch (Exception e){

        }
        patientModel.addAttribute("patients",patientService.listPatients());
        return "patient/list_patients";
    }

    // Liste
    @GetMapping(path = "/liste")
    public String list_patients(Model patientModel){
        patientModel.addAttribute("patients",patientService.listPatients());
        return "patient/list_patients";
    }

    @GetMapping(path = "/info")
    public String info(Model patientModel, @RequestParam(name = "id") Long id){
        patientModel.addAttribute("info", patientService.trouverPatient(id));
        return "patient/info_patient";
    }
}
