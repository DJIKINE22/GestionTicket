package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entities.Passager;
import com.GestionTicket.applications.MyTicket.Service.PassagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PassagerController {

    @Autowired

    private PassagerServiceImpl passagerService;

    // display list of employees

    @GetMapping("/passagers")
    public String listPassagers(Model model){

        return listUsers(1,"Nom","asc" ,model);

    }
    @GetMapping("/pagees/{pageNo}")
    public String listUsers(@PathVariable(value = "pageNo") int pageNo,
                            @RequestParam("sortField") String sortField,
                            @RequestParam("sortDir") String sortDir,
                            Model model) {
        int pageSize = 5; // Number of persons per page
        Page<Passager> page =passagerService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Passager> passagers =page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("passagers",passagers);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("renverseSortDirt", sortDir.equals("asc")? "dsc" : "asc");


        return "passagers";



    }
    @GetMapping("/addPassager")
    public  String formAddPassager(){
        return "addPassager";
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
        return "redirect:/PassagerReservation";
    }
    @PostMapping("/savePassagerB")
    public String savePassage(@ModelAttribute("passager") Passager passager) {
        // save employee to database
        passagerService.savePassager(passager);
        return "redirect:/PassagerBillet";
    }
    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("passager") Passager passager) {
        // save employee to database
        passagerService.savePassager(passager);
        return "cliey";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Passager passager = passagerService.getPassagerById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("passager", passager);
        return "UpdatePassager";
    }

    @GetMapping("/deletePassager/{id}")
    public String deletePassager(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.passagerService.deletePassagerById(id);
        return "redirect:/passagers";
    }

}
