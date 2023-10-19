package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entity.Passager;
import com.GestionTicket.applications.MyTicket.Service.PassagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PassagerController {

    @Autowired

    private PassagerService passagerService;

    // display list of employees
    @GetMapping("/index")
    public String viewHomePage(Model model) {
        model.addAttribute("listPassagers", passagerService.getAllPassager());
        return "index";
    }

    @GetMapping("/showNewPassagerForm")
    public String showNewPassagerForm(Model model) {
        // create model attribute to bind form data
        Passager passager = new Passager();
        model.addAttribute("passager", passager);
        return "new_passager";
    }

    @PostMapping("/savePassager")
    public String savePassager(@ModelAttribute("passager") Passager passager) {
        // save employee to database
        passagerService.savePassager(passager);
        return "redirect:/index";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Passager passager = passagerService.getPassagerById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("passager", passager);
        return "update_passager";
    }

    @GetMapping("/deletePassager/{id}")
    public String deletePassager(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.passagerService.deletePassagerById(id);
        return "redirect:/index";
    }

}
