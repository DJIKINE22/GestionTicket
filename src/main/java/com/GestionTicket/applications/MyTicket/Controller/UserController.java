package com.GestionTicket.applications.MyTicket.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {

    @GetMapping(path = "/home")
    public  String AfficherHome(){
        return "home";
    }
}
