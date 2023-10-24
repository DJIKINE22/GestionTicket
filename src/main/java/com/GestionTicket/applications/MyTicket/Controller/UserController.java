package com.GestionTicket.applications.MyTicket.Controller;

import com.GestionTicket.applications.MyTicket.Dto.UserDto;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Entities.User;
import com.GestionTicket.applications.MyTicket.Repository.UserRepository;
import com.GestionTicket.applications.MyTicket.Services.AuthenticationFacade;
import com.GestionTicket.applications.MyTicket.Services.UserService;
import com.GestionTicket.applications.MyTicket.Services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class UserController {

    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    AuthenticationFacade authenticationFacade;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/afficherNomUtilisateur")
    public String afficherNomUtilisateur(Model model) {
        // Obtenir l'objet Authentication de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtenir le nom de l'utilisateur actuellement connecté
        String nomUtilisateur = authentication.getName();

        // Ajouter le nom d'utilisateur au modèle pour l'affichage dans la vue
        model.addAttribute("nomUtilisateur", nomUtilisateur);

        // Retourner le nom de la vue qui affichera le nom d'utilisateur
        return "users";
    }

    @GetMapping("/registration")
    public String AfficherFormUserAjout(){
        return"FormUserAjout";
    }
    @PostMapping("/registration")
    public String AjoutUtilisateur(@ModelAttribute("users") UserDto userDto){
        userService.save(userDto);
        return "redirect:/users";

    }
    @GetMapping("/users")
    public String listUsers(Model model){

        return listUsers(1,"Nom","asc" ,model);

    }
    @GetMapping("/pages/{pageNo}")
    public String listUsers(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5; // Number of persons per page
        Page<User> page =userService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<User> users =page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users",users);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("renverseSortDirt", sortDir.equals("asc")? "dsc" : "asc");


        return "users";



    }
    @GetMapping("/home")
    public String homepage(){
        return"home";
    }
    @GetMapping("/login")
    public String Afficherlogin(){
        return"login";
    }

    @GetMapping("/admin-page")
    public String admindash(){
        return "admindashboard";
    }

    @GetMapping("/guichet")
    public String guichettierdash(){
        return "Guichet";
    }

    @GetMapping("user-page")
    public String simpleUserdash(){
        return"simpleUserDash";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationFacade.logout();
        return "redirect:/login?logout"; // Redirige vers la page de connexion avec le paramètre "logout"
    }
    @GetMapping("/UpdateUser/{id}")
    public String UpdateUserForm(@PathVariable("id") Long id, org.springframework.ui.Model model) {
        User user = userService.getUsrById(id);
        model.addAttribute("user", user);  // Ajoute le livre à l'attribut "livre" du modèle
        return "userUpdateForm";  // Assurez-vous que le nom de la vue correspond à votre configuration
    }
    @PostMapping("/modifier")
    public String modifierUtilisateur(@ModelAttribute User utilisateur) {
        userService.modifierUtilisateur(utilisateur.getId(), utilisateur);
        return "redirect:/users"; // Rediriger vers une page de liste d'utilisateurs
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

}

