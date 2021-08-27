package com.example.thym.MainController;

import com.example.thym.Form.PersonnageForm;
import com.example.thym.model.PersonnageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
/*    private String APIURL = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }*/

    private static List<PersonnageModel> personnages = new ArrayList<PersonnageModel>();

    static {
        personnages.add(new PersonnageModel( "Tyson Myke", 100, PersonnageModel.PersonnageType.GUERRIER));
        personnages.add(new PersonnageModel( "Belfort Vitor", 110, PersonnageModel.PersonnageType.MAGE));
        personnages.add(new PersonnageModel( "Khabib Nurmagomedov", 120, PersonnageModel.PersonnageType.GUERRIER));
        personnages.add(new PersonnageModel( "Badr Hari", 128, PersonnageModel.PersonnageType.MAGE));
        personnages.add(new PersonnageModel( "Badr Hary", 129, PersonnageModel.PersonnageType.MAGE));
    }

    //  Injectez (inject) via application.properties
       @Value("${welcome.message}")
    private String message = "Donjon et Dragons";

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/personnageList"}, method = RequestMethod.GET)
    public String personnageList(Model model) {

        model.addAttribute("personnages", personnages);

        return "personnageList";
    }

    @RequestMapping(value = {"/addPersonnage"}, method = RequestMethod.GET)
    public String showAddPersonnagePage(Model model) {

        PersonnageForm personnageForm = new PersonnageForm();
        model.addAttribute("personnageForm", personnageForm);

        return "addPersonnage";
    }


    @RequestMapping(value = {"/addPersonnage"}, method = RequestMethod.POST)
    public String savePersonnage(Model model, //
                                 @ModelAttribute("personnageForm") PersonnageModel personnageModel) {

        if(personnageModel.getNom()==null||personnageModel.getNom().isEmpty()){

            model.addAttribute("errorMessage", "il manque le nom");
            return "addPersonnage";
        }
        personnages.add(personnageModel);
        return "redirect:/personnageList";

    }

    //GET
    //Récupérer un personnage par son id
    @GetMapping(value = {"/Personnages/{id}"})
    public String afficherUnPersonnage(Model model, @PathVariable int id) {
        PersonnageModel personnage = null;
        for (PersonnageModel currentPersonnage : personnages) {
            if (currentPersonnage.getId() == id) {
                personnage = currentPersonnage;
            }
        }
        System.out.println(personnage.getId());
        model.addAttribute("personnage", personnage);
        return "Personnage";
    }

    @RequestMapping(value = {"/deletePersonnage/{id}"}, method = RequestMethod.DELETE)
    public String deletePersonnage(@PathVariable int id) {
        personnages.removeIf(p -> p.getId() == id);
        return "redirect:/personnageList";
    }
}


