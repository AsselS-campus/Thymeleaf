package com.example.thym.MainController;

import com.example.thym.Form.PersonnageForm;
import com.example.thym.model.PersonnageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static List<PersonnageModel> personnages=new ArrayList<PersonnageModel>();

    static {
        personnages.add(new PersonnageModel(1, "Tyson Myke", 100, "guerrier"));
        personnages.add(new PersonnageModel(2, "Belfort Vitor", 110, "guerrier"));
        personnages.add(new PersonnageModel(3, "Khabib Nurmagomedov", 120, "guerrier"));
        personnages.add(new PersonnageModel(3, "Badr Hary", 128, "guerrier"));
    }

    // Injectez (inject) via application.properties.
/*    @Value("${welcome.message}")*/
    private String message = "arzarzarzar";

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/personnageList" }, method = RequestMethod.GET)
    public String personnageList(Model model) {

        model.addAttribute("personnages", personnages);

        return "personnageList";
    }

    @RequestMapping(value = { "/addPersonnage" }, method = RequestMethod.GET)
    public String showAddPersonnagePage(Model model) {

        PersonnageForm personnageForm = new PersonnageForm();
        model.addAttribute("personnageForm", personnageForm);

        return "addPersonnage";
    }
}
