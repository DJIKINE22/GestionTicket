package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Repository.CompagnieRepository;
import com.GestionTicket.applications.MyTicket.Services.CompagnieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompagnieController {

   CompagnieService compagnieService;
   @Autowired
    CompagnieRepository compagnieRepository;



    public CompagnieController(CompagnieService compagnieService) {
        this.compagnieService = compagnieService;
    }

    @GetMapping("/newCompagnie")
    public String FormCompa() {
        return "AjoutCompa";
    }



    @GetMapping("/page/{pageNo}")
    public String listCompagnie(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
         int pageSize = 5; // Number of persons per page
        Page<Compagnie> page =compagnieService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Compagnie> compagnies =page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("compagnies",compagnies);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("renverseSortDirt", sortDir.equals("asc")? "dsc" : "asc");


        return "compagnies";



    }
    @GetMapping("/compagnies")
    public String listCompagnie(Model model){

        return listCompagnie(1,"Nom","asc" ,model);

    }



   // @GetMapping("/compagnies")
    //public ModelAndView compagniepage() {

    //List<Compagnie> list = compagnieService.allCompa();
        //return new ModelAndView("compagnielist", "compagnie", list);
    //}

    @PostMapping("RegisterCompa")
    public String AddNewCompa(@ModelAttribute Compagnie compagnie) {
        compagnieService.create(compagnie);
        return  "redirect:/compagnies";

    }
    @GetMapping("/Update/{id}")
    public String UpdateCompaForm(@PathVariable("id") Long id, org.springframework.ui.Model model) {
        Compagnie compa = compagnieService.getCompaById(id);
        model.addAttribute("compta", compa);  // Ajoute le livre à l'attribut "livre" du modèle
        return "CompaUpdateForm";  // Assurez-vous que le nom de la vue correspond à votre configuration
    }

    @GetMapping("/delete/{id}")
    public String deleteCompa(@PathVariable("id") Long id) {
        compagnieService.deleteCompaById(id);
        return "redirect:/compagnies";
    }


}
