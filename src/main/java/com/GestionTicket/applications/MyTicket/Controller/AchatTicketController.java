package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Entities.Passager;
import com.GestionTicket.applications.MyTicket.Entities.Reservation;
import com.GestionTicket.applications.MyTicket.Repository.CompagnieRepository;
import com.GestionTicket.applications.MyTicket.Service.PassagerServiceImpl;
import com.GestionTicket.applications.MyTicket.Services.BilletService;
import com.GestionTicket.applications.MyTicket.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AchatTicketController {
    @Autowired
    BilletService billetService;
    @Autowired
    PassagerServiceImpl passagerServiceImpl;
    @Autowired
    CompagnieRepository compagnieRepository;
    @Autowired
    public AchatTicketController(CompagnieRepository compagnieRepository) {
        this.compagnieRepository = compagnieRepository;
    }

    @Autowired
    ReservationService reservationService;
    public AchatTicketController(BilletService billetService) {
        this.billetService = billetService;
    }

    @GetMapping("/acheter-ticket")
    public ModelAndView ListPlace(Billet billet) {

        List<Billet> listBillets= billetService.TousBillets();
        return new ModelAndView("achatTicket", "billet" ,listBillets);
    }
    @GetMapping("/PassagerReservation")
    public String Reserver(Model model){
        List<Compagnie> ListCompagnie = compagnieRepository.findAll();
        Passager passager = passagerServiceImpl.getDernierPassager();
        model.addAttribute("ListCompagnie", ListCompagnie);
        model.addAttribute("passager", passager);
        return "maReservation";
    }

    @GetMapping("/PassagerBillet")
    public String Acheter(Model model){
        List<Compagnie> ListCompagnie = compagnieRepository.findAll();
        Passager passager = passagerServiceImpl.getDernierPassager();
        model.addAttribute("ListCompagnie", ListCompagnie);
        model.addAttribute("passager", passager);
        return "PassagerBillet";
    }


    @PostMapping("/newReservation")
    public String SaveReservation(Reservation reservation){
        reservationService.save(reservation);
        return "comfirmerReservation";
    }
    @GetMapping("/reservations")
    public ModelAndView ListReservation(Billet billet) {

        List<Reservation> listReservations= reservationService.TousResev();
        return new ModelAndView("reservations", "reservation" ,listReservations);
    }
}
