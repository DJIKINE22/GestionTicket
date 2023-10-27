package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Entities.Passager;
import com.GestionTicket.applications.MyTicket.Reposirtory.PassagerRepository;
import com.GestionTicket.applications.MyTicket.Repository.CompagnieRepository;
import com.GestionTicket.applications.MyTicket.Services.BilletService;
import com.GestionTicket.applications.MyTicket.Services.CompagnieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BilletController {
    @Autowired
    BilletService billetService;
    @Autowired
    PassagerRepository passagerRepository;
@Autowired
    CompagnieService compagnieService;

    public BilletController(CompagnieService compagnieService) {
        this.compagnieService = compagnieService;
    }

    public BilletController(PassagerRepository passagerRepository) {
        this.passagerRepository = passagerRepository;
    }

    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }



    @Autowired
    private CompagnieRepository compagnieRepository;
@Autowired
    public BilletController(CompagnieRepository compagnieRepository) {
        this.compagnieRepository = compagnieRepository;
    }

    @GetMapping("/billets")
    public ModelAndView ListBillet(Billet billet) {

        List<Billet> listBillets= billetService.TousBillets();
        return new ModelAndView("billets", "billet" ,listBillets);
    }




    @GetMapping("/newBillet")
    public String showForm(Model model) {

      List<Passager> ListPassager = passagerRepository.findAll();
        List<Compagnie> ListCompagnie = compagnieRepository.findAll();
        model.addAttribute("ListCompagnie", ListCompagnie);
        model.addAttribute("Listpassager",ListPassager);
        model.addAttribute("billet", new Billet());
        return "AjoutBillet";
    }

    @PostMapping("/saveBillet")
    public String saveBillet(@ModelAttribute Billet billet) {
            billetService.create(billet);
        return "redirect:/billets";
    }
    @PostMapping("/RegisterBillet")
    public String Billet(@ModelAttribute Billet billet) {
        billetService.create(billet);
        return "confimationBillet";
    }

    @GetMapping("/addPassagerB")
    public  String formAddPassager(){
        return "addPassagerB";
    }


}

