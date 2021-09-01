package com.example.thym.MainController;

import com.example.thym.Form.PersonnageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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
    public String savePersonnage(Model model,
                                 @ModelAttribute("personnageForm") PersonnageForm personnageForm) {
        personnageForm.setId(at.getAndIncrement());
        if(personnageForm.getNom()==null||personnageForm.getNom().isEmpty()){
            model.addAttribute("errorMessage", "il manque le nom");
            return "addPersonnage";
        }
        HttpEntity<PersonnageForm> request = new HttpEntity<>(personnageForm);
        restTemplate.postForObject("http://localhost:8081/personnages",request, PersonnageForm.class);
        return "redirect:/personnageList";
    }

    //GET.Récupérer un personnage par son id
    @GetMapping(value = {"/Personnages/{id}"})
    public String afficherUnPersonnage(Model model, @PathVariable int id) {
        PersonnageForm PersonnageForm=restTemplate.getForObject("http://localhost:8081/personnages/"+id, PersonnageForm.class);
        model.addAttribute("personnage", PersonnageForm);
        return "Personnage";
    }

    @RequestMapping(value = {"/deletePersonnage/{id}"}, method = RequestMethod.DELETE)
    public String deletePersonnage(@PathVariable int id) {
        restTemplate.delete("http://localhost:8081/personnages/"+id);
        return "redirect:/personnageList";
    }



    @RequestMapping(value = {"/updatePersonnage/{id}"}, method = RequestMethod.GET)
    public String updatePersonnagePage(Model model, @PathVariable Integer id) {
        PersonnageForm PersonnageForm=restTemplate.getForObject("http://localhost:8081/personnages/"+id, PersonnageForm.class);
        model.addAttribute("personnageForm", PersonnageForm);
        return "updatePersonnage";
    }

    @PostMapping(value={"/updatePersonnage/{id}"})
    public String updatePersonnage(@PathVariable Integer id, @ModelAttribute PersonnageForm personnageForm){

        restTemplate.put("http://localhost:8081/personnages", personnageForm);
        return "redirect:/personnageList";
    }
}


