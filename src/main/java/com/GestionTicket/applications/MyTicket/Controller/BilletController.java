package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Repository.BilletRepository;
import com.GestionTicket.applications.MyTicket.Repository.CompagnieRepository;
import com.GestionTicket.applications.MyTicket.Services.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BilletController {
    @Autowired
    BilletService billetService;

    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }

    @GetMapping("/billets")
    public ModelAndView BilletList(Billet billet) {
        List<Billet> billetlist = billetService.TousBillets();
        return new ModelAndView("billets", "billet", billetlist);
    }

    @Autowired
    private CompagnieRepository compagnieRepository;

    @GetMapping("/newBillet")
    public String FormCompa(Model model) {
        List<Compagnie> compagnies = compagnieRepository.findAll();
        model.addAttribute("compagnies", compagnies);
        return "billets";
    }


}

