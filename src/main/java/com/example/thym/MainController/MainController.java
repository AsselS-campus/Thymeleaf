package com.example.thym.MainController;

import com.example.thym.Form.PersonnageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MainController {

    private static AtomicInteger at = new AtomicInteger(1);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

//    private static List<PersonnageForm> personnages = new ArrayList<PersonnageForm>();
//    static {
//        personnages.add(new PersonnageForm(at.getAndIncrement(), "Tyson Myke", 100, PersonnageForm.PersonnageType.GUERRIER));
//        personnages.add(new PersonnageForm(at.getAndIncrement(), "Belfort Vitor", 110, PersonnageForm.PersonnageType.MAGE));
//        personnages.add(new PersonnageForm(at.getAndIncrement(), "Khabib Nurmagomedov", 120, PersonnageForm.PersonnageType.GUERRIER));
//        personnages.add(new PersonnageForm(at.getAndIncrement(), "Badr Hari", 128, PersonnageForm.PersonnageType.MAGE));
//        personnages.add(new PersonnageForm(at.getAndIncrement(), "Badr Hary", 129, PersonnageForm.PersonnageType.MAGE));
//    }

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
        // METHODE 1: on utilise directement la variable globale 'personnages'

        // METHODE 2: utilise le webservice tiers
//        ResponseEntity<PersonnageForm[]> response = restTemplate.getForEntity("http://localhost:8081/personnages", PersonnageForm[].class);
//        personnages = new ArrayList<PersonnageForm>();
//        Collections.addAll(personnages, response.getBody());

        List<PersonnageForm> personnageList = restTemplate.getForObject("http://localhost:8081/personnages", List.class);
        model.addAttribute("personnages", personnageList);
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
                                 @ModelAttribute("personnageForm") PersonnageForm personnageForm) {

        personnageForm.setId(at.getAndIncrement());
        if(personnageForm.getNom()==null||personnageForm.getNom().isEmpty()){

            model.addAttribute("errorMessage", "il manque le nom");
            return "addPersonnage";
        }
        // METHODE 1: utilise la variable globale 'personnages'
        // personnages.add(personnageForm);

        // METHODE 2: utilise le webservice tiers
        HttpEntity<PersonnageForm> request = new HttpEntity<>(personnageForm);
        restTemplate.postForObject("http://localhost:8081/personnages",request, PersonnageForm.class);

        return "redirect:/personnageList";

    }

    //GET
    //Récupérer un personnage par son id
    @GetMapping(value = {"/Personnages/{id}"})
    public String afficherUnPersonnage(Model model, @PathVariable int id) {
//        PersonnageForm personnage = null;
//        for (PersonnageForm currentPersonnage : personnages) {
//            if (currentPersonnage.getId() == id) {
//                personnage = currentPersonnage;
//            }
//        }
//        System.out.println(personnage.getId());
        //model.addAttribute("personnage", personnage);
        restTemplate.getForObject("http://localhost:8081/personnages", PersonnageForm.class);
        return "Personnage";
    }

    @RequestMapping(value = {"/deletePersonnage/{id}"}, method = RequestMethod.DELETE)
    public String deletePersonnage(@PathVariable int id) {
//        personnages.removeIf(p -> p.getId() == id);
        restTemplate.delete("http://localhost:8081/personnages/"+id);
        return "redirect:/personnageList";
    }
}


