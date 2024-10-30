package ma.emsi.dentaire.controleur;

import jakarta.servlet.http.HttpServletRequest;
import ma.emsi.dentaire.services.SituationFinanciereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/situations")
public class SituationController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "situations";
    }
    private final SituationFinanciereService situationFinanciereService;

    public SituationController(SituationFinanciereService situationFinanciereService){

        this.situationFinanciereService = situationFinanciereService;
    }

    // Liste des situations financières
    @GetMapping(path = "/liste")
    public String list_situations(Model situationModel){
        situationModel.addAttribute("situations",situationFinanciereService.listSituations());
        return "situationFinanciere/list_situations";
    }

}
