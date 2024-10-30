package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import ma.emsi.dentaire.services.DentisteService;
import ma.emsi.dentaire.services.PatientService;
import ma.emsi.dentaire.services.SecretaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private SecretaireService secretaireService;
    private DentisteService dentisteService;
    private PatientService patientService;

    public HomeController(SecretaireService secretaireService, DentisteService dentisteService, PatientService patientService){

        this.secretaireService = secretaireService;
        this.dentisteService = dentisteService;
        this.patientService = patientService;
    }

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "resume";
    }

    @GetMapping(path = "/")
    private String index(Model homeModel){
        homeModel.addAttribute("nbSecretaires", secretaireService.listSecretaires().size());
        homeModel.addAttribute("nbDentistes", dentisteService.listDentistes().size());
        homeModel.addAttribute("nbPatients", patientService.listPatients().size());
        return "home";
    }
}
